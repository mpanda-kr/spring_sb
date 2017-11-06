package com.javalec.spring_mybatis.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javalec.spring_mybatis.dto.Medicine;

@Repository
public class MedicineDaoImpl implements MedicineDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
		
	public int medicine_Dao(Medicine medicine) {
		System.out.println("impl medicine_Dao");
		return sqlSession.insert("com.javalec.spring_mybatis.dao.mapper.medicine", medicine);
		
	}
	/*
	@Override
	public List<String> getParent_list_medicine(String parent_id){
		System.out.println("parent_list_medicine  DAOIMPLµé¾î¿È");
		return sqlSession.selectList("com.javalec.spring_mybatis.dao.mapper.parent_list_medicine");
		
	}
	*/


	@Override
	public List<Medicine> parent_selectMedicineList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("parent_selectMedicineList µé¾î¿È");
		return sqlSession.selectList("com.javalec.spring_mybatis.dao.mapper.parent_medicineList", map);
	}
	
	@Override
	public List<Medicine> teacher_selectMedicineList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		System.out.println("teacher_selectMedicineList µé¾î¿È");
		return sqlSession.selectList("com.javalec.spring_mybatis.dao.mapper.teacher_medicineList", map);
	}


	
}
