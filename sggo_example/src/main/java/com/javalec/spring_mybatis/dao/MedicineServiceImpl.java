package com.javalec.spring_mybatis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalec.spring_mybatis.dto.Medicine;

@Service
public class MedicineServiceImpl implements MedicineService {
	@Autowired
	MedicineDao medicineDao;
	
	
	@Override
	public List<Medicine> parent_getMedicineList(String parent_id) {
		System.out.println("MedicineService 시작!!!!!");
		//PageHelper pageHelper = new PageHelper(page,LINE_PER_PAGE);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parent_id", parent_id);
      //  map.put("word", word);
        System.out.println("map : "+map);
        return medicineDao.parent_selectMedicineList(map);
	}
	
	
	@Override
	public List<Medicine> teacher_getMedicineList(String teacher_id) {
		System.out.println("MedicineService 시작!!!!!");
		//PageHelper pageHelper = new PageHelper(page,LINE_PER_PAGE);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("teacher_id", teacher_id);
      //  map.put("word", word);
        System.out.println("map : "+map);
        return medicineDao.teacher_selectMedicineList(map);
	}
}
