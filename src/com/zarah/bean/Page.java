package com.zarah.bean;

import java.util.List;

/**
 * 
 * @param<T>
 *      具体要分页的数据模型
 */
public class Page<T> {
	public static final Integer PAGE_SIZE=5;
	
	//当前页码
	private Integer pageNo;
	
	//总页码
	private Integer pageTotal;
	
	//总记录数
	private Integer pageTotalCount;
	
	//每页显示几条记录
	private Integer pageSize=PAGE_SIZE;
	
	//每页显示几条记录项
	private List<T> items;
	
	//分页中所有的请求地址
	private String url;
	
	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Page(Integer pageNo, Integer pageTotal, Integer pageTotalCount, Integer pageSize, List<T> items) {
		super();
		this.pageNo = pageNo;
		this.pageTotal = pageTotal;
		this.pageTotalCount = pageTotalCount;
		this.pageSize = pageSize;
		this.items = items;
	}

	public Page() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		//数据边界有效性检查
		if(pageNo<1) {
			pageNo = 1;
		}
		if(pageNo>pageTotal) {
			if(pageNo==1&&pageTotal==0) {
				pageTotal=pageNo;
			}else {
				pageNo=pageTotal;
			}
			
		}
		this.pageNo = pageNo;
	}

	public Integer getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}

	public Integer getPageTotalCount() {
		return pageTotalCount;
	}

	public void setPageTotalCount(Integer pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Page [pageNo=" + pageNo + ", pageTotal=" + pageTotal + ", pageTotalCount=" + pageTotalCount
				+ ", pageSize=" + pageSize + ", items=" + items + "]";
	}
    
	
	
	
	
}
