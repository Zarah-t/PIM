package com.zarah.bean;


public class Bill {
	private Integer id;
	private String description;
	private double money;
	private String source;
	private Integer sid;
	private String parent;
	private String date;
	private Integer uid;
	private String name;
	public Bill(Integer id, String description, double money, String source, Integer sid, String parent, String date,
			Integer uid, String name) {
		super();
		this.id = id;
		this.description = description;
		this.money = money;
		this.source = source;
		this.sid = sid;
		this.parent = parent;
		this.date = date;
		this.uid = uid;
		this.name = name;
	}
	public Bill() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", description=" + description + ", money=" + money + ", source=" + source + ", sid="
				+ sid + ", parent=" + parent + ", date=" + date + ", uid=" + uid + ", name=" + name + "]";
	}

	
	
}
