package za.co.xeon.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import za.co.xeon.domain.BillingInfo;
import za.co.xeon.repository.BillingInfoRepository;
import za.co.xeon.web.rest.dto.BillingInfoDto;
import za.co.xeon.web.rest.util.HeaderUtil;

@RestController
@RequestMapping("/api/billingInfo")
public class BillingInfoResource {

	private final Logger log = LoggerFactory.getLogger(BillingInfoResource.class);

	@Inject
	private BillingInfoRepository billingInfoRepository;

	/**
	 * GET /billingInfo/:id -> get the "id" purchaseOrder.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<BillingInfoDto> getPurchaseOrder(@PathVariable Long id) {
		log.debug("[PO:{}] - REST request to get BillingInfo");
		BillingInfoDto billingInfo = new BillingInfoDto();

		return Optional.ofNullable(billingInfo).map(result -> new ResponseEntity<>(result, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public Callable<ResponseEntity<BillingInfoDto>> calculateBill(@Valid @RequestBody BillingInfoDto billingInfo, HttpServletRequest request) throws URISyntaxException {
		log.debug("[PO:{}] - =============================================== Calculate Bill ===================================================", billingInfo.toString());
		log.debug("[PO:{}] - REST request to Calculate Billing Info", billingInfo.toString());

		return () -> {

			String locationString = billingInfo.getSource().toUpperCase() + billingInfo.getDestination().toUpperCase() + "-" + billingInfo.getSourceZone() + "-" + billingInfo.getDestinationZone();
			locationString = locationString.replace("ZONE", "Zone");

			Double calculatedVolume = billingInfo.getVolume() * 500;

			Double weight = billingInfo.getWeight();

			if (weight > calculatedVolume) {
				calculatedVolume = weight;
			}

			try{
				BillingInfo billingInfoDB = billingInfoRepository.findByLocationString(locationString, calculatedVolume);

				log.debug(calculatedVolume + "  Volume");
				if (billingInfoDB != null) {
					
					
					if(billingInfo.getServiceLevel().equalsIgnoreCase("ex"))
					{
						calculatedVolume = billingInfoDB.getExRate() * weight;

						if (billingInfoDB.getExMinRate() > calculatedVolume) {
							calculatedVolume = billingInfoDB.getExMinRate();
						}
					}else{
						calculatedVolume = billingInfoDB.getRate() * weight;

						if (billingInfoDB.getMinRate() > calculatedVolume) {
							calculatedVolume = billingInfoDB.getMinRate();
						}

					}
					
					log.debug(calculatedVolume + "  Rate");
					log.debug((calculatedVolume * 1.5)/100 + "  Etoll");
					log.debug((calculatedVolume * 4.5)/100 + "  National Tool not including");
					log.debug((calculatedVolume * 15)/100 + "  Rate");
					calculatedVolume = calculatedVolume+ (calculatedVolume * 1.5)/100+ + (calculatedVolume * 15)/100;
				}

				log.debug(calculatedVolume + " locationString : [PO:{}] - REST request to Calculated Billing Info", billingInfoDB);

				billingInfo.setCalculatedBill(calculatedVolume);
			}catch(Exception e)
			{
				log.debug("Error : "+e.getMessage());
			}

			return ResponseEntity.created(new URI("/api/billingInfo/")).headers(HeaderUtil.createCaluculationAlert("Claculatedbill", calculatedVolume + "")).body(billingInfo);
		};
	}

}
