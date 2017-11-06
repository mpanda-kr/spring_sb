package com.javalec.spring_mybatis.dao;

import java.util.List;
import java.util.Map;

import com.javalec.spring_mybatis.dto.Medicine;

public interface MedicineDao {
	
	public int medicine_Dao(Medicine medicine);
//	public String medicine_list_Dao(String parent_id);
	//public List<String> getParent_list_medicine(String parent_id);
	//public List<String> selectParent_list_medicine(Map<String, Object> map);
	
	public List<Medicine> parent_selectMedicineList(Map<String, Object>map);
	
	public List<Medicine> teacher_selectMedicineList(Map<String, Object>map);
}
