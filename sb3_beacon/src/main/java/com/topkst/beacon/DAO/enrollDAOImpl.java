package com.topkst.beacon.DAO;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.topkst.beacon.DTO.Enroll_beacon;

@Repository
public class enrollDAOImpl implements enrollDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<Enroll_beacon> center_selectBeaconList(Map<String, Object> map) {
		System.out.println("enrollDAOImpl µé¾î¿È");
		return sqlSession.selectList("com.topkst.beacon.mapper.center_enrollList",map);
	}

}
