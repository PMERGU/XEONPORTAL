package za.co.xeon.web.rest;

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
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

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
}
