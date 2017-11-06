package com.javalec.spring_mybatis.dto;

public class Medicine {
	private int num;
	private String request_date;
	private String symptom;
	private String medicine_category;
	private String medicine_amount;
	private String medicine_count;
	private String medicine_time;
	private String medicine_storage;
	private String another_content;
	private String sign_bitmap_bytecode;
	private String parent_id;
	
	public Medicine() {
		// TODO Auto-generated constructor stub
	}
	
	public Medicine(int num, String request_date, String symptom, String medicine_category, String medicine_amount, 
					String medicine_count, String medicine_time, String medicine_storage, String another_content,
					 String sign_bitmap_bytecode, String parent_id) 
	{
		this.num=num;
		this.request_date=request_date;
		this.symptom=symptom;
		this.medicine_category = medicine_category;
		this.medicine_amount = medicine_amount;
		this.medicine_count = medicine_count;
		this.medicine_time = medicine_time;
		this.medicine_storage = medicine_storage;
		this.another_content = another_content;
		this.sign_bitmap_bytecode = sign_bitmap_bytecode;
		this.parent_id = parent_id;
	}



	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getRequest_date() {
		return request_date;
	}

	public void setRequest_date(String request_date) {
		this.request_date = request_date;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getMedicine_category() {
		return medicine_category;
	}

	public void setMedicine_category(String medicine_category) {
		this.medicine_category = medicine_category;
	}

	public String getMedicine_amount() {
		return medicine_amount;
	}

	public void setMedicine_amount(String medicine_amount) {
		this.medicine_amount = medicine_amount;
	}

	public String getMedicine_count() {
		return medicine_count;
	}

	public void setMedicine_count(String medicine_count) {
		this.medicine_count = medicine_count;
	}

	public String getMedicine_time() {
		return medicine_time;
	}

	public void setMedicine_time(String medicine_time) {
		this.medicine_time = medicine_time;
	}

	public String getMedicine_storage() {
		return medicine_storage;
	}

	public void setMedicine_storage(String medicine_storage) {
		this.medicine_storage = medicine_storage;
	}

	public String getAnother_content() {
		return another_content;
	}

	public void setAnother_content(String another_content) {
		this.another_content = another_content;
	}

	public String getSign_bitmap_bytecode() {
		return sign_bitmap_bytecode;
	}

	public void setSign_bitmap_bytecode(String sign_bitmap_bytecode) {
		this.sign_bitmap_bytecode = sign_bitmap_bytecode;
	}
	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	
}
