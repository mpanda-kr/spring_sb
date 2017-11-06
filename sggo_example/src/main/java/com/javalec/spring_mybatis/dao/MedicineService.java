package com.javalec.spring_mybatis.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javalec.spring_mybatis.dto.Medicine;

@Service
public interface MedicineService {

public List<Medicine> parent_getMedicineList(String parent_id);
public List<Medicine> teacher_getMedicineList(String teacher_id);

}
