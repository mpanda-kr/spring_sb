package com.javalec.spring_mybatis.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Select;

import com.javalec.spring_mybatis.dto.Medicine;
import com.javalec.spring_mybatis.dto.User;

public interface UserDao {
	
	public ArrayList<User> listDao();
	public void writeDao(String name, String telnumber, String id, String password, String email);
	public User viewDao(String strID);
	public void deleteDao(String bId);
	public int joinusDao(User user);
	
	public String loginIdentify(String id, String password);
	public User login_Dao(User user) ; //로그인 세션
	public String chk_id_Dao(String chk_id); //ID중복 체크
	public String job_session_Dao(String id);
	
	
}
