package com.graduation.renthouse.rent.tag.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-02-26 11:08:07
 */
public class TagDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//标题描述
	private String description;
	//房屋id
	private Integer houseId;

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
	 * 设置：标题描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：标题描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：房屋id
	 */
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	/**
	 * 获取：房屋id
	 */
	public Integer getHouseId() {
		return houseId;
	}
}
