package com.javalec.spring_mybatis.dto;

public class User {
	private int num;
	private String name;
	private String telnumber;
	private String id;
	private String password;
	private String email;
	private String login_check;
	private String job;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(int num, String name, String telnumber, String id, String password, String email, String job) {
		this.num=num;
		this.name=name;
		this.telnumber=telnumber;
		this.id = id;
		this.password = password;
		this.email = email;
		this.job = job;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num=num;
	}

	
	
	public String getLogin_check() {
		return login_check;
	}

	public void setLogin_check(String login_check) {
		this.login_check = login_check;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelnumber() {
		return telnumber;
	}

	public void setTelnumber(String telnumber) {
		this.telnumber = telnumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	

	

	
}
