package com.javalec.spring_mybatis.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javalec.spring_mybatis.dto.Medicine;
import com.javalec.spring_mybatis.dto.User;

@Repository
public class UserDaoImpl implements UserDao {
	//User user;
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public String chk_id_Dao(String chk_id) {
		System.out.println("impl chk_id");
		return sqlSession.selectOne("com.javalec.spring_mybatis.dao.mapper.chk_id_Dao", chk_id);
	}
	
	@Override
	public User login_Dao(User user) {
		System.out.println("impl login_Dao");
		return sqlSession.selectOne("com.javalec.spring_mybatis.dao.mapper.login_Dao", user);
	} //실제 디비에 접근

	@Override
	public ArrayList<User> listDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeDao(String name, String telnumber, String id, String password, String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User viewDao(String strID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDao(String bId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int joinusDao(User user) {
		System.out.println("impl joinusDao");
		return sqlSession.insert("com.javalec.spring_mybatis.dao.mapper.joinusDao", user);
		
	}
	@Override
	
	public String job_session_Dao(String id) {
		System.out.println("impl job_session_Dao");
		return sqlSession.selectOne("com.javalec.spring_mybatis.dao.mapper.job_session_Dao", id);
		
	}
	@Override
	public String loginIdentify(String id, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	


}
