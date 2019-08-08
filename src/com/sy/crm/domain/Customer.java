package com.sy.crm.domain;

import java.util.HashSet;
import java.util.Set;

public class Customer {

	private Long cust_id;
	private String cust_name;
	private BaseDict baseDictsorce;
	private BaseDict baseDictindustry;
	private BaseDict baseDictlevel;
	private String cust_phone;
	private String cust_mobile;
	private String cust_image;
	private Set<LinkMan> linkMans = new HashSet<LinkMan>();
	public Set<LinkMan> getLinkMans() {
		return linkMans;
	}
	public void setLinkMans(Set<LinkMan> linkMans) {
		this.linkMans = linkMans;
	}
	public Long getCust_id() {
		return cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public BaseDict getBaseDictsorce() {
		return baseDictsorce;
	}
	public BaseDict getBaseDictindustry() {
		return baseDictindustry;
	}
	public BaseDict getBaseDictlevel() {
		return baseDictlevel;
	}
	public String getCust_phone() {
		return cust_phone;
	}
	public String getCust_mobile() {
		return cust_mobile;
	}
	public String getCust_image() {
		return cust_image;
	}
	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public void setBaseDictsorce(BaseDict baseDictsorce) {
		this.baseDictsorce = baseDictsorce;
	}
	public void setBaseDictindustry(BaseDict baseDictindustry) {
		this.baseDictindustry = baseDictindustry;
	}
	public void setBaseDictlevel(BaseDict baseDictlevel) {
		this.baseDictlevel = baseDictlevel;
	}
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}
	public void setCust_image(String cust_image) {
		this.cust_image = cust_image;
	}
	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", baseDictsorce=" + baseDictsorce
				+ ", baseDictindustry=" + baseDictindustry + ", baseDictlevel=" + baseDictlevel + ", cust_phone="
				+ cust_phone + ", cust_mobile=" + cust_mobile + ", cust_image=" + cust_image + "]";
	}

}
