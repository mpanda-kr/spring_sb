package com.javalec.spring_mybatis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.javalec.spring_mybatis.dto.User;

public abstract class ContentDao implements UserDao{

	public static int login_result;
	JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public ContentDao() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public ArrayList<User> listDao() {
		String query = "select * from member order by id desc";
		ArrayList<User> dtos = (ArrayList<User>) template.query(query, new BeanPropertyRowMapper<User>(User.class));
		return dtos;
	}
	
	
	public void writeDao(final String name, final String telnumber, final String id, final String password, final String email) {
		System.out.println("writeDao()");
		
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				String query = "insert into member (name, telnumber, id, password, email) values (?, ?, ?, ?, ?)";
		
				PreparedStatement pstmt = con.prepareStatement(query);
			
				pstmt.setString(1, name);
				pstmt.setString(2, telnumber);
				pstmt.setString(3, id);
				pstmt.setString(4, password);
				pstmt.setString(5, email);
				
				
				return pstmt;
			}
		});
		
	}

	
	@Override
	public User viewDao(String strID) {
		System.out.println("viewDao()");
		
		String query = "select * from member where id = " + strID;
		return template.queryForObject(query, new BeanPropertyRowMapper<User>(User.class));
	}

	
	@Override
	public void deleteDao(final String bId) {
		System.out.println("deleteDao()");
		
		String query = "delete from member where id = ?";
		this.template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.parseInt(bId));
			}
		});
		
	}


	

}
