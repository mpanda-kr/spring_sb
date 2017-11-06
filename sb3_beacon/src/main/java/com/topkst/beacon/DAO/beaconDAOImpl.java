package com.topkst.beacon.DAO;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topkst.beacon.DTO.Beacon_scan;

@Repository
public class beaconDAOImpl implements beaconDAO {
	@Autowired
	   private SqlSessionTemplate sqlSession;
	
	
	 public void scanDao(Beacon_scan beacon_scan) {
	      System.out.println("impl scanDao");
	      sqlSession.insert("com.topkst.beacon.mapper.beacon_scan", beacon_scan);      
}
}