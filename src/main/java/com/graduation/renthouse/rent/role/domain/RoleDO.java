package com.graduation.renthouse.rent.role.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-03-11 15:06:07
 */
public class RoleDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer roleId;
	//角色名称
	private String name;
	//权限所有
	private String sign;
	//创建时间
	private Date createtime;
	//修改时间
	private Date updatetime;


	List<Integer> perIds;

	public List<Integer> getPerIds() {
		return perIds;
	}

	public void setPerIds(List<Integer> perIds) {
		this.perIds = perIds;
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
	 * 设置：角色名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：角色名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：权限所有
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}
	/**
	 * 获取：权限所有
	 */
	public String getSign() {
		return sign;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdatetime() {
		return updatetime;
	}
}
