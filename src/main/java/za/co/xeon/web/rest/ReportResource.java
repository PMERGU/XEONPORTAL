package za.co.xeon.web.rest;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import za.co.xeon.domain.Attachment;
import za.co.xeon.domain.Company;
import za.co.xeon.domain.User;
import za.co.xeon.external.sap.hibersap.HiberSapService;
import za.co.xeon.external.sap.hibersap.forge.so.dto.EtCustOrders;
import za.co.xeon.external.sap.hibersap.forge.sr.dto.StockInventory;
import za.co.xeon.repository.AttachmentRepository;
import za.co.xeon.repository.CompanyRepository;
import za.co.xeon.repository.UserRepository;
import za.co.xeon.web.rest.dto.IORReportReqDTO;
import za.co.xeon.web.rest.dto.PODReportReqDTO;
import za.co.xeon.web.rest.dto.PODReportResDTO;
import za.co.xeon.web.rest.dto.StockReportDTO;
import za.co.xeon.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/api")
public class ReportResource {
	private final Logger log = LoggerFactory.getLogger(ReportResource.class);
	@Inject
	private HiberSapService hiberSapService;
	@Inject
	private UserRepository userRepository;
	@Inject
	private CompanyRepository companyRepository;
	@Inject
	private AttachmentRepository attachmentRepository;

	@RequestMapping(value = "/stockReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public Callable<ResponseEntity<List<StockInventory>>> fetchStockData(@Valid @RequestBody StockReportDTO dto, Pageable pageable) {
		log.debug("[Report:{}] - REST request to Fetch Stock Data for Report", dto.toString());
		log.debug("Pageable options for Stock Report :: " + pageable.getOffset() + " :: " + pageable.getPageNumber() + " :: " + pageable.getPageSize());
		return () -> {
			List<StockInventory> ret = new ArrayList<StockInventory>();
			try {
				Company company = companyRepository.findOne(Long.valueOf(dto.getCompany()));
				dto.setCompany(company.getMaterialName());
				ret = hiberSapService.fetchStockData(dto);
			} catch (Exception e) {
				log.debug("Error : " + e.getMessage());
			}
			Page<StockInventory> page = new PageImpl<StockInventory>(ret, pageable, ret.size());
			HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stockReport/");
			return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
		};
	}

	@RequestMapping(value = "/stockReport/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public Callable<ResponseEntity<List<StockInventory>>> fetchStockDataByUser(@PathVariable String login, Pageable pageable) {
		log.debug("[Reportz:{}] - REST request to Fetch Stock Data for Report By User Id", login);
		log.debug("Pageable options for Stock Report :: " + pageable.getOffset() + " :: " + pageable.getPageNumber() + " :: " + pageable.getPageSize());
		return () -> {
			List<StockInventory> ret = new ArrayList<StockInventory>();
			try {
				Optional<User> user = userRepository.findOneByLogin(login);
				String company = user.get().getCompany().getMaterialName();
				StockReportDTO dto = new StockReportDTO();
				dto.setCompany(company);
				ret = hiberSapService.fetchStockData(dto);
				log.debug("[Reportz:{}] - REST request to Fetch Stock Data for Report By User size", ret.size());
			} catch (Exception e) {
				e.printStackTrace();
				log.debug("Errorss : " + e.getMessage());
			}
			Page<StockInventory> page = new PageImpl<StockInventory>(ret, pageable, ret.size());
			HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stockReport/" + login);
			return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
		};
	}

	@RequestMapping(value = "/podReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public Callable<ResponseEntity<List<PODReportResDTO>>> fetchPODData(@Valid @RequestBody PODReportReqDTO dto, HttpServletRequest request) {
		log.debug("[Report:{}] - REST request to Fetch Stock Data for Report", dto.toString());
		return () -> {
			List<PODReportResDTO> ret = new ArrayList<PODReportResDTO>();
			try {
				List<EtCustOrders> evResult = hiberSapService.getCustomerOrdersForPOD(dto.getSapId(), dto.getFromDate(), dto.getToDate(), dto.getPodType(),dto.getOrderType());
				for (EtCustOrders evr : evResult) {
					PODReportResDTO resDto = new PODReportResDTO();
					BeanUtils.copyProperties(resDto, evr);
					List<Attachment> att = attachmentRepository.findByDeliveryNumberAndPOD(evr.get_dbeln());
					if (att != null && att.size() > 0)
						resDto.setPodStatus("Y");
					else
						resDto.setPodStatus("N");
					att = attachmentRepository.findByDeliveryNumberAndInv(evr.get_dbeln());
					if (att != null && att.size() > 0)
						resDto.setInvStatus("Y");
					else
						resDto.setInvStatus("N");
					ret.add(resDto);
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.debug("Errorss : " + e.getMessage());
			}
			return ResponseEntity.created(new URI("/api/podReport/")).body(ret);
		};

	}

	@RequestMapping(value = "/iorReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public Callable<ResponseEntity<List<za.co.xeon.external.sap.hibersap.forge.ior.dto.EtCustOrders>>> fetchIORData(@Valid @RequestBody IORReportReqDTO dto, HttpServletRequest request) {
		log.debug("[Report:{}] - REST request to Fetch Stock Data for Report", dto.toString());
		return () -> {
			List<za.co.xeon.external.sap.hibersap.forge.ior.dto.EtCustOrders> ret = new ArrayList<za.co.xeon.external.sap.hibersap.forge.ior.dto.EtCustOrders>();
			try {
				ret = hiberSapService.getCustomerOrdersForIOR(dto.getSapId(), dto.getFromDate(), dto.getToDate(), dto.getPodType());
			} catch (Exception e) {
				e.printStackTrace();
				log.debug("Errorss : " + e.getMessage());
			}
			return ResponseEntity.created(new URI("/api/iorReport/")).body(ret);
		};

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/stockReport/download", method = RequestMethod.POST, produces = "application/pdf")
	public @ResponseBody ResponseEntity downloadStockDataByUser(@Valid @RequestBody StockReportDTO dto) throws Exception {
		log.debug("[Reportz:{}] - REST request to Fetch Stock Data for Report By User Id", dto);

		List<StockInventory> ret = new ArrayList<StockInventory>();
		try {
			Company company = companyRepository.findOne(Long.valueOf(dto.getCompany()));
			dto.setCompany(company.getMaterialName());
			ret = hiberSapService.fetchStockData(dto);
			log.debug("[Reportz:{}] - REST request to Fetch Stock Data for Report By User size", ret.size());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Errorss : " + e.getMessage());
		}

		ByteArrayOutputStream pdfFile;
		byte[] input;
		try {
			pdfFile = createStockReportPdf(ret, dto.getCompany());
			input = pdfFile.toByteArray();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.setContentDispositionFormData("StockReport", "StockReport");
			headers.setContentLength(input.length);
			return new ResponseEntity<byte[]>(input, headers, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/podReport/download", method = RequestMethod.POST, produces = "application/pdf")
	public @ResponseBody ResponseEntity downloadPODDataByUser(@Valid @RequestBody PODReportReqDTO dto) throws Exception {
		log.debug("[Reportz:{}] - REST request to Fetch Stock Data for Report By User Id", dto);

		List<PODReportResDTO> ret = new ArrayList<PODReportResDTO>();
		try {
			List<EtCustOrders> evResult = hiberSapService.getCustomerOrdersForPOD(dto.getSapId(), dto.getFromDate(), dto.getToDate(), dto.getPodType(),dto.getOrderType());
			for (EtCustOrders evr : evResult) {
				PODReportResDTO resDto = new PODReportResDTO();
				BeanUtils.copyProperties(resDto, evr);
				List<Attachment> att = attachmentRepository.findByDeliveryNumberAndPOD(evr.get_dbeln());
				if (att != null && att.size() > 0)
					resDto.setPodStatus("Y");
				else
					resDto.setPodStatus("N");
				att = attachmentRepository.findByDeliveryNumberAndInv(evr.get_dbeln());
				if (att != null && att.size() > 0)
					resDto.setInvStatus("Y");
				else
					resDto.setInvStatus("N");
				ret.add(resDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Errorss : " + e.getMessage());
		}

		ByteArrayOutputStream pdfFile;
		byte[] input;
		try {
			pdfFile = createPODReportPdf(ret, (dto.getSapId() != null || dto.getSapId().trim().length() != 0) ? companyRepository.findBySapId(dto.getSapId()).getName() : "");
			input = pdfFile.toByteArray();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			headers.setContentDispositionFormData("StockReport", "StockReport");
			headers.setContentLength(input.length);
			return new ResponseEntity<byte[]>(input, headers, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
	}

	public ByteArrayOutputStream createStockReportPdf(List<StockInventory> ret, String string) {
		Document document = new Document(PageSize.A3);
		try {
			Float fntSize = 6.7f;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, out);
			writer.setPageEvent(new InventoryReportHeader(string));
			document.open();
			PdfPTable table = new PdfPTable(10); // 9 columns.
			table.setWidthPercentage(100); // Width 100%
			table.setSpacingBefore(10f); // Space before table
			table.setSpacingAfter(10f); // Space after table

			// Set Column widths
			float[] columnWidths = { 0.3f, 0.5f, 1f, 2f, 1f, 0.5f, 0.5f, 1f, 0.5f, 0.5f };
			table.setWidths(columnWidths);

			PdfPCell iDHeader = new PdfPCell(new Paragraph("ID", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			iDHeader.setBorderColor(BaseColor.BLACK);
			iDHeader.setPaddingLeft(10);
			iDHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			iDHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(iDHeader);

			PdfPCell wnHeader = new PdfPCell(new Paragraph("Warehouse Number", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			wnHeader.setBorderColor(BaseColor.BLACK);
			wnHeader.setPaddingLeft(10);
			wnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			wnHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(wnHeader);

			PdfPCell mtHeader = new PdfPCell(new Paragraph("Material", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			mtHeader.setBorderColor(BaseColor.BLACK);
			mtHeader.setPaddingLeft(10);
			mtHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			mtHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(mtHeader);

			PdfPCell mtDsHeader = new PdfPCell(new Paragraph("Material Description", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			mtDsHeader.setBorderColor(BaseColor.BLACK);
			mtDsHeader.setPaddingLeft(10);
			mtDsHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			mtDsHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(mtDsHeader);

			PdfPCell stHeader = new PdfPCell(new Paragraph("Storage Type", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			stHeader.setBorderColor(BaseColor.BLACK);
			stHeader.setPaddingLeft(10);
			stHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			stHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(stHeader);

			PdfPCell batchCell = new PdfPCell(new Paragraph("Batch ", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			batchCell.setBorderColor(BaseColor.BLACK);
			batchCell.setPaddingLeft(10);
			batchCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			batchCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(batchCell);

			PdfPCell putAwayQnant = new PdfPCell(new Paragraph("Put ways", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			putAwayQnant.setBorderColor(BaseColor.BLACK);
			putAwayQnant.setPaddingLeft(10);
			putAwayQnant.setHorizontalAlignment(Element.ALIGN_CENTER);
			putAwayQnant.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(putAwayQnant);

			PdfPCell pickQuant = new PdfPCell(new Paragraph("Quantity to Remove", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			pickQuant.setBorderColor(BaseColor.BLACK);
			pickQuant.setPaddingLeft(10);
			pickQuant.setHorizontalAlignment(Element.ALIGN_CENTER);
			pickQuant.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(pickQuant);

			PdfPCell availableQunatity = new PdfPCell(new Paragraph("Available", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			availableQunatity.setBorderColor(BaseColor.BLACK);
			availableQunatity.setPaddingLeft(10);
			availableQunatity.setHorizontalAlignment(Element.ALIGN_CENTER);
			availableQunatity.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(availableQunatity);

			PdfPCell uom = new PdfPCell(new Paragraph("UOM", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			uom.setBorderColor(BaseColor.BLACK);
			uom.setPaddingLeft(10);
			uom.setHorizontalAlignment(Element.ALIGN_CENTER);
			uom.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(uom);

			if (ret != null && ret.size() > 0) {
				int i = 1;
				for (StockInventory data : ret) {
					PdfPCell iDdata = new PdfPCell(new Paragraph(i + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					iDdata.setBorderColor(BaseColor.BLACK);
					iDdata.setPaddingLeft(10);
					iDdata.setHorizontalAlignment(Element.ALIGN_CENTER);
					iDdata.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(iDdata);

					PdfPCell wnData = new PdfPCell(new Paragraph(data.get_lgnumt(), FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					wnData.setBorderColor(BaseColor.BLACK);
					wnData.setPaddingLeft(10);
					wnData.setHorizontalAlignment(Element.ALIGN_CENTER);
					wnData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(wnData);

					PdfPCell mtData = new PdfPCell(new Paragraph(data.get_matnr(), FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					mtData.setBorderColor(BaseColor.BLACK);
					mtData.setPaddingLeft(10);
					mtData.setHorizontalAlignment(Element.ALIGN_CENTER);
					mtData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(mtData);

					PdfPCell mtDsData = new PdfPCell(new Paragraph(data.get_maktx(), FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					mtDsData.setBorderColor(BaseColor.BLACK);
					mtDsData.setPaddingLeft(10);
					mtDsData.setHorizontalAlignment(Element.ALIGN_CENTER);
					mtDsData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(mtDsData);

					PdfPCell stData = new PdfPCell(new Paragraph(data.get_ltypt(), FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					stData.setBorderColor(BaseColor.BLACK);
					stData.setPaddingLeft(10);
					stData.setHorizontalAlignment(Element.ALIGN_CENTER);
					stData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(stData);

					PdfPCell batchCellData = new PdfPCell(new Paragraph(data.get_charg(), FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					batchCellData.setBorderColor(BaseColor.BLACK);
					batchCellData.setPaddingLeft(10);
					batchCellData.setHorizontalAlignment(Element.ALIGN_CENTER);
					batchCellData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(batchCellData);

					PdfPCell putAwayQnantData = new PdfPCell(new Paragraph(data.get_einme() + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					putAwayQnantData.setBorderColor(BaseColor.BLACK);
					putAwayQnantData.setPaddingLeft(10);
					putAwayQnantData.setHorizontalAlignment(Element.ALIGN_CENTER);
					putAwayQnantData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(putAwayQnantData);

					PdfPCell pickQuantData = new PdfPCell(new Paragraph(data.get_einme() + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					pickQuantData.setBorderColor(BaseColor.BLACK);
					pickQuantData.setPaddingLeft(10);
					pickQuantData.setHorizontalAlignment(Element.ALIGN_CENTER);
					pickQuantData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(pickQuantData);

					PdfPCell availableQunatityData = new PdfPCell(new Paragraph(data.get_verme() + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					availableQunatityData.setBorderColor(BaseColor.BLACK);
					availableQunatityData.setPaddingLeft(10);
					availableQunatityData.setHorizontalAlignment(Element.ALIGN_CENTER);
					availableQunatityData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(availableQunatityData);

					PdfPCell uomData = new PdfPCell(new Paragraph(data.get_meins() + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					uomData.setBorderColor(BaseColor.BLACK);
					uomData.setPaddingLeft(10);
					uomData.setHorizontalAlignment(Element.ALIGN_CENTER);
					uomData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(uomData);
					i++;
				}
			}
			document.add(table);

			document.close();
			writer.close();
			return out;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public ByteArrayOutputStream createPODReportPdf(List<PODReportResDTO> ret, String string) {
		Document document = new Document(PageSize.A3);
		try {
			Float fntSize = 6.7f;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, out);
			writer.setPageEvent(new PODReportHeader(string));
			document.open();
			PdfPTable table = new PdfPTable(11); // 9 columns.
			table.setWidthPercentage(100); // Width 100%
			table.setSpacingBefore(5f); // Space before table
			table.setSpacingAfter(5f); // Space after table

			// Set Column widths
			float[] columnWidths = { 0.7f, 0.5f, 0.7f, 0.7f, 0.7f, 0.7f, 0.7f, 0.7f, 0.7f, 0.5f, 0.5f };
			table.setWidths(columnWidths);

			PdfPCell iDHeader = new PdfPCell(new Paragraph("Purchase Order", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			iDHeader.setBorderColor(BaseColor.BLACK);
			iDHeader.setPaddingLeft(10);
			iDHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			iDHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(iDHeader);

			PdfPCell wnHeader = new PdfPCell(new Paragraph("Consumer Controller", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			wnHeader.setBorderColor(BaseColor.BLACK);
			wnHeader.setPaddingLeft(10);
			wnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			wnHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(wnHeader);

			PdfPCell mtHeader = new PdfPCell(new Paragraph("Captured By", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			mtHeader.setBorderColor(BaseColor.BLACK);
			mtHeader.setPaddingLeft(10);
			mtHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			mtHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(mtHeader);

			PdfPCell stHeader = new PdfPCell(new Paragraph("Pickup Party", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			stHeader.setBorderColor(BaseColor.BLACK);
			stHeader.setPaddingLeft(10);
			stHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			stHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(stHeader);

			PdfPCell mtDsHeader = new PdfPCell(new Paragraph("Pickup HUB", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			mtDsHeader.setBorderColor(BaseColor.BLACK);
			mtDsHeader.setPaddingLeft(10);
			mtDsHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			mtDsHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(mtDsHeader);

			PdfPCell batchCell = new PdfPCell(new Paragraph("Ship To Party", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			batchCell.setBorderColor(BaseColor.BLACK);
			batchCell.setPaddingLeft(10);
			batchCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			batchCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(batchCell);

			PdfPCell putAwayQnant = new PdfPCell(new Paragraph("Consignee", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			putAwayQnant.setBorderColor(BaseColor.BLACK);
			putAwayQnant.setPaddingLeft(10);
			putAwayQnant.setHorizontalAlignment(Element.ALIGN_CENTER);
			putAwayQnant.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(putAwayQnant);

			PdfPCell pickQuant = new PdfPCell(new Paragraph("Sales Order", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			pickQuant.setBorderColor(BaseColor.BLACK);
			pickQuant.setPaddingLeft(10);
			pickQuant.setHorizontalAlignment(Element.ALIGN_CENTER);
			pickQuant.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(pickQuant);

			PdfPCell availableQunatity = new PdfPCell(new Paragraph("Delivery No", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			availableQunatity.setBorderColor(BaseColor.BLACK);
			availableQunatity.setPaddingLeft(10);
			availableQunatity.setHorizontalAlignment(Element.ALIGN_CENTER);
			availableQunatity.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(availableQunatity);

			PdfPCell uom = new PdfPCell(new Paragraph("POD Status", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			uom.setBorderColor(BaseColor.BLACK);
			uom.setPaddingLeft(10);
			uom.setHorizontalAlignment(Element.ALIGN_CENTER);
			uom.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(uom);

			PdfPCell inv = new PdfPCell(new Paragraph("Invoice Status", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			inv.setBorderColor(BaseColor.BLACK);
			inv.setPaddingLeft(10);
			inv.setHorizontalAlignment(Element.ALIGN_CENTER);
			uom.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(inv);

			if (ret != null && ret.size() > 0) {
				for (PODReportResDTO data : ret) {
					PdfPCell iDdata = new PdfPCell(new Paragraph(data.get_bstkd(), FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					iDdata.setBorderColor(BaseColor.BLACK);
					iDdata.setPaddingLeft(10);
					iDdata.setHorizontalAlignment(Element.ALIGN_CENTER);
					iDdata.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(iDdata);

					PdfPCell wnData = new PdfPCell(new Paragraph(data.get_parvwYe(), FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					wnData.setBorderColor(BaseColor.BLACK);
					wnData.setPaddingLeft(10);
					wnData.setHorizontalAlignment(Element.ALIGN_CENTER);
					wnData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(wnData);

					PdfPCell mtData = new PdfPCell(new Paragraph(data.get_ernam(), FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					mtData.setBorderColor(BaseColor.BLACK);
					mtData.setPaddingLeft(10);
					mtData.setHorizontalAlignment(Element.ALIGN_CENTER);
					mtData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(mtData);

					PdfPCell mtDsData = new PdfPCell(new Paragraph(data.get_parvwYc(), FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					mtDsData.setBorderColor(BaseColor.BLACK);
					mtDsData.setPaddingLeft(10);
					mtDsData.setHorizontalAlignment(Element.ALIGN_CENTER);
					mtDsData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(mtDsData);

					PdfPCell stData = new PdfPCell(new Paragraph(data.get_parvwYh(), FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					stData.setBorderColor(BaseColor.BLACK);
					stData.setPaddingLeft(10);
					stData.setHorizontalAlignment(Element.ALIGN_CENTER);
					stData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(stData);

					PdfPCell batchCellData = new PdfPCell(new Paragraph(data.get_parvwWe(), FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					batchCellData.setBorderColor(BaseColor.BLACK);
					batchCellData.setPaddingLeft(10);
					batchCellData.setHorizontalAlignment(Element.ALIGN_CENTER);
					batchCellData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(batchCellData);

					PdfPCell putAwayQnantData = new PdfPCell(new Paragraph(data.get_parvwAg() + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					putAwayQnantData.setBorderColor(BaseColor.BLACK);
					putAwayQnantData.setPaddingLeft(10);
					putAwayQnantData.setHorizontalAlignment(Element.ALIGN_CENTER);
					putAwayQnantData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(putAwayQnantData);

					PdfPCell pickQuantData = new PdfPCell(new Paragraph(data.get_vbeln() + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					pickQuantData.setBorderColor(BaseColor.BLACK);
					pickQuantData.setPaddingLeft(10);
					pickQuantData.setHorizontalAlignment(Element.ALIGN_CENTER);
					pickQuantData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(pickQuantData);

					PdfPCell availableQunatityData = new PdfPCell(new Paragraph(data.get_dbeln() + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					availableQunatityData.setBorderColor(BaseColor.BLACK);
					availableQunatityData.setPaddingLeft(10);
					availableQunatityData.setHorizontalAlignment(Element.ALIGN_CENTER);
					availableQunatityData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(availableQunatityData);

					PdfPCell uomData = new PdfPCell(new Paragraph(data.getPodStatus() + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					uomData.setBorderColor(BaseColor.BLACK);
					uomData.setPaddingLeft(10);
					uomData.setHorizontalAlignment(Element.ALIGN_CENTER);
					uomData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(uomData);

					PdfPCell invData = new PdfPCell(new Paragraph(data.getInvStatus() + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					invData.setBorderColor(BaseColor.BLACK);
					invData.setPaddingLeft(10);
					invData.setHorizontalAlignment(Element.ALIGN_CENTER);
					invData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(invData);
				}
			}
			document.add(table);

			document.close();
			writer.close();
			return out;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	class InventoryReportHeader extends PdfPageEventHelper {
		String companyName;

		public InventoryReportHeader(String companyName) {
			this.companyName = companyName;
		}

		Font ffont = new Font(Font.FontFamily.UNDEFINED, 20, Font.ITALIC);
		Font rfont = new Font(Font.FontFamily.UNDEFINED, 20, Font.ITALIC);
		Font lfont = new Font(Font.FontFamily.UNDEFINED, 20, Font.ITALIC);

		public void onEndPage(PdfWriter writer, Document document) {
			PdfContentByte cb = writer.getDirectContent();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			Phrase hdrDate = new Phrase(sdf.format(new Date()), ffont);
			Phrase hdrCenter = new Phrase("Inventory Report", ffont);
			Phrase hdrCompany = new Phrase(companyName, lfont);
			ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, hdrDate, (document.right() - 25), document.top() + 10, 0);
			ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, hdrCenter, (document.right() - document.left()) / 2 + document.leftMargin(), document.top() + 10, 0);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, hdrCompany, 50, document.top() + 10, 0);
		}
	}

	class PODReportHeader extends PdfPageEventHelper {
		String companyName;

		public PODReportHeader(String companyName) {
			this.companyName = companyName;
		}

		Font ffont = new Font(Font.FontFamily.UNDEFINED, 20, Font.ITALIC);
		Font rfont = new Font(Font.FontFamily.UNDEFINED, 20, Font.ITALIC);
		Font lfont = new Font(Font.FontFamily.UNDEFINED, 20, Font.ITALIC);

		public void onEndPage(PdfWriter writer, Document document) {
			PdfContentByte cb = writer.getDirectContent();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			Phrase hdrDate = new Phrase(sdf.format(new Date()), ffont);
			Phrase hdrCenter = new Phrase("POD Report", ffont);
			Phrase hdrCompany = new Phrase(companyName, lfont);
			ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, hdrDate, (document.right() - 25), document.top() + 10, 0);
			ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, hdrCenter, (document.right() - document.left()) / 2 + document.leftMargin(), document.top() + 10, 0);
			ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, hdrCompany, 50, document.top() + 10, 0);
		}
	}

}
