package za.co.xeon.web.rest;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import za.co.xeon.external.sap.hibersap.HiberSapService;
import za.co.xeon.external.sap.hibersap.dto.StockData;
import za.co.xeon.web.rest.dto.StockReportDTO;

@RestController
@RequestMapping("/api")
public class ReportResource {
	@Inject
	private HiberSapService hiberSapService;

	@RequestMapping(value = "/stockReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StockData>> fetchStockData(StockReportDTO dto) {
		try {
			return new ResponseEntity<List<StockData>>(hiberSapService.fetchStockData(dto), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
