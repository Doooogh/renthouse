package com.graduation.renthouse.rent.tenant.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-02-23 10:43:10
 */
public class TenantDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//租客名字
	private String name;
	//性别
	private String sex;
	//手机号
	private String phone;

	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：租客名字
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：租客名字
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号
	 */
	public String getPhone() {
		return phone;
	}
}
