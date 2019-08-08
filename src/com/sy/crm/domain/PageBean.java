package com.sy.crm.domain;

import java.util.List;

public class PageBean<T> {
	private Integer currentpage;
	private Integer pagesize;
	private Integer totalpage;
	private Integer totalcount;
	private List<T> list;
	public Integer getCurrentpage() {
		return currentpage;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public Integer getTotalpage() {
		return totalpage;
	}
	public Integer getTotalcount() {
		return totalcount;
	}
	public List<T> getList() {
		return list;
	}
	public void setCurrentpage(Integer currentpage) {
		this.currentpage = currentpage;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public void setTotalpage(Integer totalpage) {
		this.totalpage = totalpage;
	}
	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}
