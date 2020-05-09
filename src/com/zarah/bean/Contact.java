package com.zarah.bean;

public class Contact {
	private Integer id;
	private String contact;
	private String phone;
	private String wechat;
	private String qq;
	private String birth;
	private String address;
	private Integer userId;
	
	public Contact(Integer id, String contact, String phone, String wechat, String qq, String birth, String address,
			Integer userId) {
		super();
		this.id = id;
		this.contact = contact;
		this.phone = phone;
		this.wechat = wechat;
		this.qq = qq;
		this.birth = birth;
		this.address = address;
		this.userId = userId;
	}
	
	public Contact() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWechat() {
		return wechat;
	}
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getUserId() {
		return userId;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", contact=" + contact + ", phone=" + phone + ", wechat=" + wechat + ", qq=" + qq
				+ ", birth=" + birth + ", address=" + address + ", userId=" + userId + "]";
	}
	
	
}
