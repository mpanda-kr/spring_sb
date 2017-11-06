package com.topkst.beacon.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.topkst.beacon.DTO.Enroll_beacon;

@Service
public interface EnrollService {
	public List<Enroll_beacon> center_getEnrollBeacon(String center_id, String gateway_id);
}
