package com.topkst.beacon.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topkst.beacon.DAO.enrollDAO;
import com.topkst.beacon.DTO.Enroll_beacon;

@Service
public class EnrollServiceImpl implements EnrollService{
	@Autowired
	enrollDAO enrollDAO;
	   	   
	@Override
	public List<Enroll_beacon> center_getEnrollBeacon(String center_id, String gateway_id) {
		  System.out.println("enroll Service Ω√¿€!!!!!");
		  Map<String, Object> map = new HashMap<String, Object>();
		  map.put("center_id", center_id);
		  map.put("gateway_id", gateway_id);

	      System.out.println("map : "+map);

		return  enrollDAO.center_selectBeaconList(map);
	}
}
