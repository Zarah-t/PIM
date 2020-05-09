package com.zarah.bean;

public class Password {
	private Integer id;
	private String pname;
	private String pass;
	private Integer uid;
	public Password(Integer id, String pname, String pass, Integer uid) {
		super();
		this.id = id;
		this.pname = pname;
		this.pass = pass;
		this.uid = uid;
	}
	public Password() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Password [id=" + id + ", pname=" + pname + ", pass=" + pass + ", uid=" + uid + "]";
	}

	
	
}
