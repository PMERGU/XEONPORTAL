package za.co.xeon.web.rest;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
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
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import za.co.xeon.domain.User;
import za.co.xeon.external.sap.hibersap.HiberSapService;
import za.co.xeon.external.sap.hibersap.dto.StockData;
import za.co.xeon.repository.UserRepository;
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

	@RequestMapping(value = "/stockReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public Callable<ResponseEntity<List<StockData>>> fetchStockData(@Valid @RequestBody StockReportDTO dto, HttpServletRequest request) {
		log.debug("[Report:{}] - REST request to Fetch Stock Data for Report", dto.toString());
		return () -> {
			List<StockData> ret = new ArrayList<StockData>();
			try {
				ret = hiberSapService.fetchStockData(dto);
			} catch (Exception e) {
				log.debug("Error : " + e.getMessage());
			}
			return ResponseEntity.created(new URI("/api/stockReport/")).body(ret);
		};
	}

	@RequestMapping(value = "/stockReport/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public Callable<ResponseEntity<List<StockData>>> fetchStockDataByUser(@PathVariable String login, Pageable pageable) {
		log.debug("[Reportz:{}] - REST request to Fetch Stock Data for Report By User Id", login);
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
	        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stockReport/"+login);
	        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
		};
	}
	
	@RequestMapping(value = "/stockReport/download/{login}", method = RequestMethod.GET  ,produces = "application/pdf")
	public @ResponseBody ResponseEntity  downloadStockDataByUser(@PathVariable String login) throws Exception {
		log.debug("[Reportz:{}] - REST request to Fetch Stock Data for Report By User Id", login);
		 
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
			 
			 ByteArrayOutputStream pdfFile;
			 byte[] input;
			try {
				pdfFile =createPdf(ret);
				input = pdfFile.toByteArray();
				HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.parseMediaType("application/pdf"));
			    headers.setContentDispositionFormData("StockReport", "StockReport");
			    headers.setContentLength(input.length);
			    return new ResponseEntity<byte[]>(input,headers,HttpStatus.OK);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw e;
			} 
	}
	
	
	 
	public  ByteArrayOutputStream createPdf(List<StockData> ret) {
		Document document = new Document();
	    try
	    {
//	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("AddTableExample.pdf"));
	    	ByteArrayOutputStream out = new ByteArrayOutputStream();
	    	PdfWriter writer = PdfWriter.getInstance(document, out);
	        document.open();
	 
	        PdfPTable table = new PdfPTable(7); // 3 columns.
	        table.setWidthPercentage(100); //Width 100%
	        table.setSpacingBefore(10f); //Space before table
	        table.setSpacingAfter(10f); //Space after table
	 
	        //Set Column widths
	        float[] columnWidths = {0.5f, 1f, 1f,1f, 1f, 1f,1f};
	        table.setWidths(columnWidths);
	 
	        PdfPCell iDHeader = new PdfPCell(new Paragraph("ID"));
	        iDHeader.setBorderColor(BaseColor.BLACK);
	        iDHeader.setPaddingLeft(10);
	        iDHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
	        iDHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 
	        PdfPCell wnHeader = new PdfPCell(new Paragraph("Warehouse Number"));
	        wnHeader.setBorderColor(BaseColor.BLACK);
	        wnHeader.setPaddingLeft(10);
	        wnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
	        wnHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 
	        PdfPCell mtHeader = new PdfPCell(new Paragraph("Material "));
	        mtHeader.setBorderColor(BaseColor.BLACK);
	        mtHeader.setPaddingLeft(10);
	        mtHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
	        mtHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        
	        
	        PdfPCell availableQunatity = new PdfPCell(new Paragraph("Available Quantity "));
	        availableQunatity.setBorderColor(BaseColor.BLACK);
	        availableQunatity.setPaddingLeft(10);
	        availableQunatity.setHorizontalAlignment(Element.ALIGN_CENTER);
	        availableQunatity.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        
	        
	        PdfPCell batchCell = new PdfPCell(new Paragraph("Batch "));
	        batchCell.setBorderColor(BaseColor.BLACK);
	        batchCell.setPaddingLeft(10);
	        batchCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        batchCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        
	        PdfPCell pickQuant = new PdfPCell(new Paragraph("Pick Quantity "));
	        pickQuant.setBorderColor(BaseColor.BLACK);
	        pickQuant.setPaddingLeft(10);
	        pickQuant.setHorizontalAlignment(Element.ALIGN_CENTER);
	        pickQuant.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        
	        PdfPCell putAwayQnant = new PdfPCell(new Paragraph("Put Away Quantity "));
	        pickQuant.setBorderColor(BaseColor.BLACK);
	        pickQuant.setPaddingLeft(10);
	        pickQuant.setHorizontalAlignment(Element.ALIGN_CENTER);
	        pickQuant.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        
	        table.addCell(iDHeader);
	        table.addCell(wnHeader);
	        table.addCell(mtHeader);
	        table.addCell(batchCell);
	        table.addCell(pickQuant);
	        table.addCell(putAwayQnant);
	        table.addCell(availableQunatity);
	        if(ret!=null && ret.size()>0){
	        	int i=1;
	        	for(StockData data:ret){
	        		PdfPCell iDdata = new PdfPCell(new Paragraph(i+""));
	        		iDdata.setBorderColor(BaseColor.BLUE);
	        		iDdata.setPaddingLeft(10);
	        		iDdata.setHorizontalAlignment(Element.ALIGN_CENTER);
	        		iDdata.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    	 
	    	        PdfPCell warehouseNumberData = new PdfPCell(new Paragraph(data.get_lgnum()));
	    	        warehouseNumberData.setBorderColor(BaseColor.BLACK);
	    	        warehouseNumberData.setPaddingLeft(10);
	    	        warehouseNumberData.setHorizontalAlignment(Element.ALIGN_CENTER);
	    	        warehouseNumberData.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    	 
	    	        PdfPCell materialData = new PdfPCell(new Paragraph(data.get_matnr()));
	    	        materialData.setBorderColor(BaseColor.BLACK);
	    	        materialData.setPaddingLeft(10);
	    	        materialData.setHorizontalAlignment(Element.ALIGN_CENTER);
	    	        materialData.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    	        
	    	        
	    	      
	    	        
	    	        
	    	        PdfPCell batchData = new PdfPCell(new Paragraph(""));
	    	        batchData.setBorderColor(BaseColor.BLACK);
	    	        batchData.setPaddingLeft(10);
	    	        batchData.setHorizontalAlignment(Element.ALIGN_CENTER);
	    	        batchData.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    	        
	    	        PdfPCell pickQuantData = new PdfPCell(new Paragraph(""));
	    	        pickQuantData.setBorderColor(BaseColor.BLACK);
	    	        pickQuantData.setPaddingLeft(10);
	    	        pickQuantData.setHorizontalAlignment(Element.ALIGN_CENTER);
	    	        pickQuantData.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    	        
	    	        PdfPCell putAwayQnantData = new PdfPCell(new Paragraph(data.get_lgtyp()));
	    	        putAwayQnantData.setBorderColor(BaseColor.BLACK);
	    	        putAwayQnantData.setPaddingLeft(10);
	    	        putAwayQnantData.setHorizontalAlignment(Element.ALIGN_CENTER);
	    	        putAwayQnantData.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    	        
	    	        PdfPCell availableQunatityData = new PdfPCell(new Paragraph(data.get_verme()+""));
	    	        availableQunatityData.setBorderColor(BaseColor.BLACK);
	    	        availableQunatityData.setPaddingLeft(10);
	    	        availableQunatityData.setHorizontalAlignment(Element.ALIGN_CENTER);
	    	        availableQunatityData.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    	        
	    	        
	    	        table.addCell(iDHeader);
	    	        table.addCell(warehouseNumberData);
	    	        table.addCell(materialData);
	    	        table.addCell(batchData);
	    	        table.addCell(pickQuantData);
	    	        table.addCell(putAwayQnantData);
	    	        table.addCell(availableQunatityData);
	        	}
	        }
	        //To avoid having the cell border and the content overlap, if you are having thick cell borders
	        //cell1.setUserBorderPadding(true);
	        //cell2.setUserBorderPadding(true);
	        //cell3.setUserBorderPadding(true);
	 
	        
	        
	 
	        document.add(table);
	 
	        document.close();
	        writer.close();
	        return out;
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	    
	    return null;
	}
	
}
