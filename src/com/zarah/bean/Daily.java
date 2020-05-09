package com.zarah.bean;


public class Daily {
	
	private Integer id;
	private String arrDate;
	private String place;
	private String description;
	private Integer userId;
	
	
	public Daily() {
		super();
		// TODO 自动生成的构造函数存根
	}


	public Daily(Integer id, String arrDate, String place, String description, Integer userId) {
		super();
		this.id = id;
		this.arrDate = arrDate;
		this.place = place;
		this.description = description;
		this.userId = userId;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getArrDate() {
		return arrDate;
	}


	public void setArrDate(String arrDate) {
		this.arrDate = arrDate;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "Daily [id=" + id + ", arrDate=" + arrDate + ", place=" + place + ", description=" + description
				+ ", userId=" + userId + "]";
	}
	
	







}
