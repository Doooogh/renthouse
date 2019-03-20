package com.graduation.renthouse.rent.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-03-05 18:15:19
 */
public class OrderDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//编号
	private String number;
	//房东id
	private Integer landlordId;
	//租客id
	private Integer tenantId;
	//房子id
	private Integer houseId;
	//租期
	private String tenancyTerm;
	//价格
	private Integer price;
	//状态
	private Integer status;
	//订单生成日期
	@JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss" ,  timezone="GMT+8")
	private Date createdate;
	//订单修改日期
	@JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss" ,  timezone="GMT+8")
	private Date updatedate;

	public OrderDO() {
	}

	public OrderDO(String number, Integer landlordId, Integer tenantId, Integer houseId, String tenancyTerm, Integer price, Integer status, Date createdate, Date updatedate) {
		this.number = number;
		this.landlordId = landlordId;
		this.tenantId = tenantId;
		this.houseId = houseId;
		this.tenancyTerm = tenancyTerm;
		this.price = price;
		this.status = status;
		this.createdate = createdate;
		this.updatedate = updatedate;
	}

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
	 * 设置：编号
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * 获取：编号
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * 设置：房东id
	 */
	public void setLandlordId(Integer landlordId) {
		this.landlordId = landlordId;
	}
	/**
	 * 获取：房东id
	 */
	public Integer getLandlordId() {
		return landlordId;
	}
	/**
	 * 设置：租客id
	 */
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	/**
	 * 获取：租客id
	 */
	public Integer getTenantId() {
		return tenantId;
	}
	/**
	 * 设置：房子id
	 */
	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}
	/**
	 * 获取：房子id
	 */
	public Integer getHouseId() {
		return houseId;
	}
	/**
	 * 设置：租期
	 */
	public void setTenancyTerm(String tenancyTerm) {
		this.tenancyTerm = tenancyTerm;
	}
	/**
	 * 获取：租期
	 */
	public String getTenancyTerm() {
		return tenancyTerm;
	}
	/**
	 * 设置：价格
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 * 获取：价格
	 */
	public Integer getPrice() {
		return price;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：订单生成日期
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	/**
	 * 获取：订单生成日期
	 */
	public Date getCreatedate() {
		return createdate;
	}
	/**
	 * 设置：订单修改日期
	 */
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	/**
	 * 获取：订单修改日期
	 */
	public Date getUpdatedate() {
		return updatedate;
	}
}
