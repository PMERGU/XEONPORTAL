package za.co.xeon.service;

import za.co.xeon.domain.enumeration.ServiceType;

public class ReportRFCTest {
	public static void main(String[] args) {
		for (ServiceType type : ServiceType.values()) {
			System.out.println(type + " :: " + type.getSapCode());
		}
	}
}
