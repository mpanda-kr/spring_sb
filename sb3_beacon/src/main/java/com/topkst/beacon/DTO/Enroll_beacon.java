package com.topkst.beacon.DTO;

public class Enroll_beacon {
	private String enroll_beacon_uuid;
	
	public Enroll_beacon() {}
	

	public Enroll_beacon(String enroll_beacon_uuid) {
		super();
		this.enroll_beacon_uuid = enroll_beacon_uuid;
	}


	public String getEnroll_beacon_uuid() {
		return enroll_beacon_uuid;
	}

	public void setEnroll_beacon_uuid(String enroll_beacon_uuid) {
		this.enroll_beacon_uuid = enroll_beacon_uuid;
	}


}
