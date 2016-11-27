package za.co.xeon.domain.enumeration;

/**
 * The ServiceLevel enumeration.
 */
public enum VehicleSize {
	HEAVY_CONTAINER_12m("12M-HEAVY"), LIGHT_CONTAINER_12m("12M-LIGHT"), EXTRA_HEAVY_CONTAINER_6m("6M-EX-HEAVY"), LIGHT_CONTAINER_6m("6M-LIGHT"), HEAVY_CONTAINER_6m("6M-HEAVY"), FULL_TRUCK_6m("6M_FTL_CSD"), FULL_TRUCK_12m("12M_FTL_CSD"), TRUCK_1_TON("1TN_FTL_CLSD"), TRUCK_1__5_TON("1.5TN_FTL_DPD"), FULL_TRUCK_18m("18M_FTL_CSD"), FULL_TRUCK_4_TON("4TN_FTL_DPD"), FULL_TRUCK_5_TON("5TN_FTL_DPD"), FULL_TRUCK_8_TON("8TN_FTL_DPD");

	private String sapCode;

	VehicleSize(String sapCode) {
		this.sapCode = sapCode;
	}

	public String getSapCode() {
		return sapCode;
	}
}
