package com.graduation.renthouse.rent.img.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-02-26 11:04:54
 */
public class ImgDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//图片src
	private String src;
	//房屋id
	private Integer houseId;

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
	 * 设置：图片src
	 */
	public void setSrc(String src) {
		this.src = src;
	}
	/**
	 * 获取：图片src
	 */
	public String getSrc() {
		return src;
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
