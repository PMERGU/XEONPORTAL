package za.co.xeon.domain.enumeration;

/**
 * The DeliveryType enumeration.
 */
public enum BillingInfoTypes implements SapCode {

	BFN("BFN"), CPT("CPT"), DUR("DUR"), ELS("ELS"), GRG("GRG"), JNB("JNB"), KIM("KIM"), KLD("KLD"), NLP("NLP"), PLZ("PLZ"), PTG("PTG");
	

	private String sapCode;

	BillingInfoTypes(String sapCode) {
		this.sapCode = sapCode;
	}

	public String getSapCode() {
		return sapCode;
	}
}
