package com.zarah.bean;

public class Sort {
	private Integer id;
	private String name;
	private String parent;
	private Integer userId;
	public Sort() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Sort(Integer id, String name, String parent, Integer userId) {
		super();
		this.id = id;
		this.name = name;
		this.parent = parent;
		this.userId = userId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Sort [id=" + id + ", name=" + name + ", parent=" + parent + ", userId=" + userId + "]";
	}
	
	


}
