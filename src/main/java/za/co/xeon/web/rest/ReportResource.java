package za.co.xeon.web.rest;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import za.co.xeon.domain.Attachment;
import za.co.xeon.domain.PurchaseOrder;
import za.co.xeon.domain.User;
import za.co.xeon.external.sap.hibersap.HiberSapService;
import za.co.xeon.external.sap.hibersap.dto.GtCustOrders;
import za.co.xeon.external.sap.hibersap.dto.StockData;
import za.co.xeon.repository.AttachmentRepository;
import za.co.xeon.repository.PurchaseOrderRepository;
import za.co.xeon.repository.UserRepository;
import za.co.xeon.web.rest.dto.PODReportReqDTO;
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
	private PurchaseOrderRepository purchaseOrderRepository;
	@Inject
	private AttachmentRepository attachmentRepository;

	@RequestMapping(value = "/stockReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public Callable<ResponseEntity<List<StockData>>> fetchStockData(@Valid @RequestBody StockReportDTO dto, Pageable pageable) {
		log.debug("[Report:{}] - REST request to Fetch Stock Data for Report", dto.toString());
		log.debug("Pageable options for Stock Report :: " + pageable.getOffset() + " :: " + pageable.getPageNumber() + " :: " + pageable.getPageSize());
		return () -> {
			List<StockData> ret = new ArrayList<StockData>();
			try {
				ret = hiberSapService.fetchStockData(dto);
			} catch (Exception e) {
				log.debug("Error : " + e.getMessage());
			}
			Page<StockData> page = new PageImpl<StockData>(ret, pageable, ret.size());
			HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stockReport/");
			return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
		};
	}

	@RequestMapping(value = "/stockReport/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public Callable<ResponseEntity<List<StockData>>> fetchStockDataByUser(@PathVariable String login, Pageable pageable) {
		log.debug("[Reportz:{}] - REST request to Fetch Stock Data for Report By User Id", login);
		log.debug("Pageable options for Stock Report :: " + pageable.getOffset() + " :: " + pageable.getPageNumber() + " :: " + pageable.getPageSize());
		return () -> {
			List<StockData> ret = new ArrayList<StockData>();
			try {
				Optional<User> user = userRepository.findOneByLogin(login);
				String company = user.get().getCompany().getName();
				StockReportDTO dto = new StockReportDTO();
				dto.setCompany(company);
				ret = hiberSapService.fetchStockData(dto);
				log.debug("[Reportz:{}] - REST request to Fetch Stock Data for Report By User size", ret.size());
			} catch (Exception e) {
				e.printStackTrace();
				log.debug("Errorss : " + e.getMessage());
			}
			Page<StockData> page = new PageImpl<StockData>(ret, pageable, ret.size());
			HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stockReport/" + login);
			return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
		};
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/stockReport/download", method = RequestMethod.POST, produces = "application/pdf")
	public @ResponseBody ResponseEntity downloadStockDataByUser(@Valid @RequestBody StockReportDTO dto) throws Exception {
		log.debug("[Reportz:{}] - REST request to Fetch Stock Data for Report By User Id", dto);

		List<StockData> ret = new ArrayList<StockData>();
		try {
			// Company company = companyRepository.findOne(comapanyId);
			// String companyName = company.getName();
			// StockReportDTO dto = new StockReportDTO();
			// dto.setCompany(companyName);
			ret = hiberSapService.fetchStockData(dto);
			log.debug("[Reportz:{}] - REST request to Fetch Stock Data for Report By User size", ret.size());
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Errorss : " + e.getMessage());
		}

		ByteArrayOutputStream pdfFile;
		byte[] input;
		try {
			pdfFile = createPdf(ret);
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

	public ByteArrayOutputStream createPdf(List<StockData> ret) {
		Document document = new Document(PageSize.A3);
		try {
			// PdfWriter writer = PdfWriter.getInstance(document, new
			// FileOutputStream("AddTableExample.pdf"));
			Float fntSize=6.7f;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.open();
			PdfPTable table = new PdfPTable(10); // 3 columns.
			table.setWidthPercentage(100); // Width 100%
			table.setSpacingBefore(10f); // Space before table
			table.setSpacingAfter(10f); // Space after table

			// Set Column widths
			float[] columnWidths = { 0.3f, 0.5f, 1f, 2f, 1f, 0.5f, 0.5f,1f, 0.5f, 0.5f };
			table.setWidths(columnWidths);

			PdfPCell iDHeader = new PdfPCell(new Paragraph("ID" , FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
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
			
			PdfPCell availableQunatity = new PdfPCell(new Paragraph("Available", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			availableQunatity.setBorderColor(BaseColor.BLACK);
			availableQunatity.setPaddingLeft(10);
			availableQunatity.setHorizontalAlignment(Element.ALIGN_CENTER);
			availableQunatity.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(availableQunatity);
			

			PdfPCell pickQuant = new PdfPCell(new Paragraph("Picks", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			pickQuant.setBorderColor(BaseColor.BLACK);
			pickQuant.setPaddingLeft(10);
			pickQuant.setHorizontalAlignment(Element.ALIGN_CENTER);
			pickQuant.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(pickQuant);
			
			PdfPCell uomHeader = new PdfPCell(new Paragraph("UoM", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
			uomHeader.setBorderColor(BaseColor.BLACK);
			uomHeader.setPaddingLeft(10);
			uomHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
			uomHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.addCell(uomHeader);
			

			 
			if (ret != null && ret.size() > 0) {
				int i = 1;
				for (StockData data : ret) {
					PdfPCell iDdata = new PdfPCell(new Paragraph(i + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					iDdata.setBorderColor(BaseColor.BLACK);
					iDdata.setPaddingLeft(10);
					iDdata.setHorizontalAlignment(Element.ALIGN_CENTER);
					iDdata.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(iDdata);
					 
					PdfPCell wnData = new PdfPCell(new Paragraph(data.get_lgnum(), FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
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
					
					PdfPCell stData = new PdfPCell(new Paragraph(data.get_lgtyp(), FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
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
					
					PdfPCell putAwayQnantData = new PdfPCell(new Paragraph(data.get_einme()+"", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					putAwayQnantData.setBorderColor(BaseColor.BLACK);
					putAwayQnantData.setPaddingLeft(10);
					putAwayQnantData.setHorizontalAlignment(Element.ALIGN_CENTER);
					putAwayQnantData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(putAwayQnantData);
					
					PdfPCell availableQunatityData = new PdfPCell(new Paragraph(data.get_verme()+"", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					availableQunatityData.setBorderColor(BaseColor.BLACK);
					availableQunatityData.setPaddingLeft(10);
					availableQunatityData.setHorizontalAlignment(Element.ALIGN_CENTER);
					availableQunatityData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(availableQunatityData);
					

					PdfPCell pickQuantData = new PdfPCell(new Paragraph(data.get_einme()+"", FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
					pickQuantData.setBorderColor(BaseColor.BLACK);
					pickQuantData.setPaddingLeft(10);
					pickQuantData.setHorizontalAlignment(Element.ALIGN_CENTER);
					pickQuantData.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(pickQuantData);
					
					PdfPCell uomData = new PdfPCell(new Paragraph(data.get_meins(), FontFactory.getFont(FontFactory.TIMES_ROMAN, fntSize)));
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
	@RequestMapping(value = "/podReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public Callable<ResponseEntity<List<GtCustOrders>>> fetchPODData(@Valid @RequestBody PODReportReqDTO dto, HttpServletRequest request) {
		log.debug("[Report:{}] - REST request to Fetch Stock Data for Report", dto.toString());
		return () -> {
			List<GtCustOrders> ret = new ArrayList<GtCustOrders>();
			try {
				List<GtCustOrders> custOrders = null;
				if (dto.getPodType() != null)
					custOrders = hiberSapService.getCustomerOrdersForPODStatus(dto.getFromDate(), dto.getToDate(), dto.getPodType());
				else
					custOrders = hiberSapService.getCustomerOrdersForPOD(dto.getFromDate(), dto.getToDate());
				for (GtCustOrders order : custOrders) {
					PurchaseOrder po = purchaseOrderRepository.getOne(Long.valueOf(order.getBstkd()));
					List<Attachment> atts = attachmentRepository.findByPONumberAndPOD(po.getId());
					boolean pod = false;
					for (Attachment att : atts) {
						pod = pod && att.getCategory().equalsIgnoreCase("POD");
						if (pod) {
							if (dto.getId() != null) {
								if (dto.getId().longValue() == po.getUser().getCompany().getId().longValue())
									ret.add(order);
							} else
								ret.add(order);
						}
					}
				}
			} catch (Exception e) {
				log.debug("Error : " + e.getMessage());
			}
			return ResponseEntity.created(new URI("/api/stockReport/")).body(ret);
		};
	}

}
