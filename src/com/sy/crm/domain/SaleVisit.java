package com.sy.crm.domain;

import java.util.Date;

/*
`visit_id` VARCHAR(32) NOT NULL,
`visit_cust_id` BIGINT(32) DEFAULT NULL COMMENT '�ͻ�id',
`visit_user_id` BIGINT(32) DEFAULT NULL COMMENT '������id',
`visit_time` DATETIME DEFAULT NULL COMMENT '�ݷ�ʱ��',
`visit_addr` VARCHAR(128) DEFAULT NULL COMMENT '�ݷõص�',
`visit_detail` VARCHAR(256) DEFAULT NULL COMMENT '�ݷ�����',
`visit_nexttime` DATE DEFAULT NULL COMMENT '�´ΰݷ�ʱ��',-*/
public class SaleVisit {
	private Long visit_id;
	private Date visit_time;
	private String visit_addr;
	private String visit_detail;
	private Date visit_nexttime;
	private Customer customer;
	private User user;
	public SaleVisit(){
	}

	
	public Date getVisit_time() {
		return visit_time;
	}
	@Override
	public String toString() {
		return "SaleVisit [visit_id=" + visit_id + ", visit_time=" + visit_time + ", visit_addr=" + visit_addr
				+ ", visit_detail=" + visit_detail + ", visit_nexttime=" + visit_nexttime + ", customer=" + customer
				+ ", user=" + user + "]";
	}


	public String getVisit_addr() {
		return visit_addr;
	}
	public String getVisit_detail() {
		return visit_detail;
	}

	public Customer getCustomer() {
		return customer;
	}
	public User getUser() {
		return user;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Long getVisit_id() {
		return visit_id;
	}
	public void setVisit_id(Long visit_id) {
		this.visit_id = visit_id;
	}
	public void setVisit_time(Date visit_time) {
		this.visit_time = visit_time;
	}
	public void setVisit_addr(String visit_addr) {
		this.visit_addr = visit_addr;
	}
	public void setVisit_detail(String visit_detail) {
		this.visit_detail = visit_detail;
	}

	public Date getVisit_nexttime() {
		return visit_nexttime;
	}


	public void setVisit_nexttime(Date visit_nexttime) {
		this.visit_nexttime = visit_nexttime;
	}


	public void setCustomer_(Customer customer) {
		this.customer = customer;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
