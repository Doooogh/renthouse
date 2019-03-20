package com.graduation.renthouse.rent.roleper.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-03-13 15:54:55
 */
public class RolePerDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer roleId;
	//
	private Integer perId;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	/**
	 * 获取：
	 */
	public Integer getRoleId() {
		return roleId;
	}
	/**
	 * 设置：
	 */
	public void setPerId(Integer perId) {
		this.perId = perId;
	}
	/**
	 * 获取：
	 */
	public Integer getPerId() {
		return perId;
	}
}
