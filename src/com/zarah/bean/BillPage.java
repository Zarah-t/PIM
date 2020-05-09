package com.zarah.bean;

import java.util.List;

public class BillPage<T> {
	private double in;
	private double out;
	private List<T> items;
	public double getIn() {
		return in;
	}
	public void setIn(double in) {
		this.in = in;
	}
	public double getOut() {
		return out;
	}
	public void setOut(double out) {
		this.out = out;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public BillPage(double in, double out, List<T> items) {
		super();
		this.in = in;
		this.out = out;
		this.items = items;
	}
	public BillPage() {
		super();
		// TODO 自动生成的构造函数存根
	}
	@Override
	public String toString() {
		return "BillPage [in=" + in + ", out=" + out + ", items=" + items + "]";
	}
	
	

}
