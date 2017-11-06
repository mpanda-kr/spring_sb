package com.topkst.beacon.DTO;

import java.sql.Date;
import java.sql.Time;


public class Beacon_scan {

	private Date createdate;
	
	private Time createtime;
	
	private String detailtime;
	
	private String mac_address;
	
	private String uuid;
	
	private int major;
	
	private int minor;
	
	private int rssi;
	
	private int tx_power;
	
	public Beacon_scan() {}

	
	public Beacon_scan(Date createdate, Time createtime, String detailtime, String mac_address, String uuid, int major,
			int minor, int rssi, int tx_power) {
		super();
		this.createdate = createdate;
		this.createtime = createtime;
		this.detailtime = detailtime;
		this.mac_address = mac_address;
		this.uuid = uuid;
		this.major = major;
		this.minor = minor;
		this.rssi = rssi;
		this.tx_power = tx_power;
	}


	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Time getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Time createtime) {
		this.createtime = createtime;
	}

	public String getDetailtime() {
		return detailtime;
	}

	public void setDetailtime(String detailtime) {
		this.detailtime = detailtime;
	}

	public String getMac_address() {
		return mac_address;
	}

	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getMajor() {
		return major;
	}

	public void setMajor(int major) {
		this.major = major;
	}

	public int getMinor() {
		return minor;
	}

	public void setMinor(int minor) {
		this.minor = minor;
	}

	public int getRssi() {
		return rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	public int getTx_power() {
		return tx_power;
	}

	public void setTx_power(int tx_power) {
		this.tx_power = tx_power;
	}
	
	
		
		
		
}
