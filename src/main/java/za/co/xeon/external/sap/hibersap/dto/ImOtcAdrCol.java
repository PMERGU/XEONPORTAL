package za.co.xeon.external.sap.hibersap.dto;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

/**
 * Created by Derick on 9/11/2016.
 */
@BapiStructure
public class ImOtcAdrCol {
	/**
	 * "Form of address"
	 */
	@Parameter("TITLE")
	private String title;

	/**
	 * "Name 1"
	 */
	@Parameter("NAME")
	private String name;

	/**
	 * "Name 2"
	 */
	@Parameter("NAME_2")
	private String name2;

	/**
	 * "House number and street"
	 */
	@Parameter("STREET")
	private String street;

	/**
	 * "City"
	 */
	@Parameter("CITY")
	private String city;

	/**
	 * "District"
	 */
	@Parameter("DISTRICT")
	private String district;

	/**
	 * "Region (State, Province, County)"
	 */
	@Parameter("REGION")
	private String region;

	/**
	 * "Transportation zone to or from which the goods are delivered"
	 */
	@Parameter("TRANSPZONE")
	private String transpzone;

	/**
	 * "Country Key"
	 */
	@Parameter("COUNTRY")
	private String country;

	/**
	 * "Postal Code"
	 */
	@Parameter("POSTL_CODE")
	private String postlCode;

	/**
	 * "P.O. Box Postal Code"
	 */
	@Parameter("POBX_PCD")
	private String pobxPcd;

	/**
	 * "PO Box city"
	 */
	@Parameter("POBX_CTY")
	private String pobxCty;

	/**
	 * "First telephone number"
	 */
	@Parameter("TELEPHONE")
	private String telephone;

	/**
	 * "Fax Number"
	 */
	@Parameter("FAX_NUMBER")
	private String faxNumber;

	public ImOtcAdrCol(String title, String name, String name2, String street, String city, String district, String region, String transpzone, String country, String postlCode, String pobxPcd, String pobxCty, String telephone, String faxNumber) {
		this.title = title;
		this.name = name;
		this.name2 = name2;
		this.street = street;
		this.city = city;
		this.district = district;
		this.region = region;
		this.transpzone = transpzone;
		this.country = country;
		this.postlCode = postlCode;
		this.pobxPcd = pobxPcd;
		this.pobxCty = pobxCty;
		this.telephone = telephone;
		this.faxNumber = faxNumber;
	}

	public ImOtcAdrCol() {
	}

	/**
	 * @return "Title" - "Form of address"
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return "Name" - "Name 1"
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return "Name2" - "Name 2"
	 */
	public String getName2() {
		return name2;
	}

	/**
	 * @return "Street" - "House number and street"
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @return "City" - "City"
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return "District" - "District"
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @return "Region" - "Region (State, Province, County)"
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @return "Transpzone" -
	 *         "Transportation zone to or from which the goods are delivered"
	 */
	public String getTranspzone() {
		return transpzone;
	}

	/**
	 * @return "Country" - "Country Key"
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return "PostlCode" - "Postal Code"
	 */
	public String getPostlCode() {
		return postlCode;
	}

	/**
	 * @return "PobxPcd" - "P.O. Box Postal Code"
	 */
	public String getPobxPcd() {
		return pobxPcd;
	}

	/**
	 * @return "PobxCty" - "PO Box city"
	 */
	public String getPobxCty() {
		return pobxCty;
	}

	/**
	 * @return "Telephone" - "First telephone number"
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @return "FaxNumber" - "Fax Number"
	 */
	public String getFaxNumber() {
		return faxNumber;
	}
}
