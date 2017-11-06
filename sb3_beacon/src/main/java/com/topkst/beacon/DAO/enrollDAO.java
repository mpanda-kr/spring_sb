package com.topkst.beacon.DAO;

import java.util.List;
import java.util.Map;

import com.topkst.beacon.DTO.Enroll_beacon;

public interface enrollDAO {
	public List<Enroll_beacon> center_selectBeaconList (Map<String, Object>map);
}
