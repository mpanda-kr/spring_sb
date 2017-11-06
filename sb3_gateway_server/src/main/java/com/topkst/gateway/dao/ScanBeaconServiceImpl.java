package com.topkst.gateway.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topkst.gateway.dto.ScanBeacon;

@Service
public class ScanBeaconServiceImpl implements ScanBeaconService {

	@Autowired
	ScanBeaconDAO scanbeaconDAO;
	
	@Override
	public void setScanBeacon(ScanBeacon beacon) {
		System.out.println("ScanBeacon Service Impl ����");
		scanbeaconDAO.setScanBeacon_insert(beacon);
	}

}
