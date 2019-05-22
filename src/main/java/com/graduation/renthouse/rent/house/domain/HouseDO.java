package com.graduation.renthouse.rent.house.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-02-22 17:18:56
 */
public class HouseDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//房屋标题
	private String title;
	//大范围地址
	private String largeAreas;
	//小范围地址
	private String smallAreas;
	//房屋平米数
	private String squareMeter;
	//房屋朝向
	private String orientation;
	//房屋类型
	private String houseType;
	//发布时间
	private String pubdate;
	//价格
	private Integer price;
	//房屋描述
	private String description;
	//详细地址
	private String address;
	//具体发布时间
	private String addTime;
	//房东id
	private Integer landlordId;
	//租客id
	private Integer tenantId;
	//是否有电梯
	private Integer elevator;
	//是否有电视
	private Integer tv;
	//是否有冰箱
	private Integer fridge;
	//是否有空调
	private Integer airConditioner;
	//是否有宽带
	private Integer broadBand;
	//是否有衣柜
	private Integer wardrobe;

	private Integer uId;
	//
	private Date createtime;
	//
	private Date updatetime;
	//
	private Integer status;

	public HouseDO() {
	}

	public HouseDO(Integer id, String title, String largeAreas, String smallAreas, String squareMeter, String orientation, String houseType, String pubdate, Integer price, String description, String address, String addTime, Integer landlordId, Integer tenantId, Integer elevator, Integer tv, Integer fridge, Integer airConditioner, Integer broadBand, Integer wardrobe, Date createtime, Date updatetime, Integer status) {
		this.id = id;
		this.title = title;
		this.largeAreas = largeAreas;
		this.smallAreas = smallAreas;
		this.squareMeter = squareMeter;
		this.orientation = orientation;
		this.houseType = houseType;
		this.pubdate = pubdate;
		this.price = price;
		this.description = description;
		this.address = address;
		this.addTime = addTime;
		this.landlordId = landlordId;
		this.tenantId = tenantId;
		this.elevator = elevator;
		this.tv = tv;
		this.fridge = fridge;
		this.airConditioner = airConditioner;
		this.broadBand = broadBand;
		this.wardrobe = wardrobe;
		this.createtime = createtime;
		this.updatetime = updatetime;
		this.status = status;
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
	 * 设置：房屋标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：房屋标题
	 */
	public String getTitle() {
		return title;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	/**
	 * 设置：大范围地址
	 */
	public void setLargeAreas(String largeAreas) {
		this.largeAreas = largeAreas;
	}
	/**
	 * 获取：大范围地址
	 */
	public String getLargeAreas() {
		return largeAreas;
	}
	/**
	 * 设置：小范围地址
	 */
	public void setSmallAreas(String smallAreas) {
		this.smallAreas = smallAreas;
	}
	/**
	 * 获取：小范围地址
	 */
	public String getSmallAreas() {
		return smallAreas;
	}
	/**
	 * 设置：房屋平米数
	 */
	public void setSquareMeter(String squareMeter) {
		this.squareMeter = squareMeter;
	}
	/**
	 * 获取：房屋平米数
	 */
	public String getSquareMeter() {
		return squareMeter;
	}
	/**
	 * 设置：房屋朝向
	 */
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	/**
	 * 获取：房屋朝向
	 */
	public String getOrientation() {
		return orientation;
	}
	/**
	 * 设置：房屋类型
	 */
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	/**
	 * 获取：房屋类型
	 */
	public String getHouseType() {
		return houseType;
	}
	/**
	 * 设置：发布时间
	 */
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	/**
	 * 获取：发布时间
	 */
	public String getPubdate() {
		return pubdate;
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
	 * 设置：房屋描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：房屋描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：详细地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：具体发布时间
	 */
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：具体发布时间
	 */
	public String getAddTime() {
		return addTime;
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
	 * 设置：房东id
	 */
	public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
	/**
	 * 获取：房东id
	 */
	public Integer getTenantId() {
		return tenantId;
	}
	/**
	 * 设置：是否有电梯
	 */
	public void setElevator(Integer elevator) {
		this.elevator = elevator;
	}
	/**
	 * 获取：是否有电梯
	 */
	public Integer getElevator() {
		return elevator;
	}
	/**
	 * 设置：是否有电视
	 */
	public void setTv(Integer tv) {
		this.tv = tv;
	}
	/**
	 * 获取：是否有电视
	 */
	public Integer getTv() {
		return tv;
	}
	/**
	 * 设置：是否有冰箱
	 */
	public void setFridge(Integer fridge) {
		this.fridge = fridge;
	}
	/**
	 * 获取：是否有冰箱
	 */
	public Integer getFridge() {
		return fridge;
	}
	/**
	 * 设置：是否有空调
	 */
	public void setAirConditioner(Integer airConditioner) {
		this.airConditioner = airConditioner;
	}
	/**
	 * 获取：是否有空调
	 */
	public Integer getAirConditioner() {
		return airConditioner;
	}
	/**
	 * 设置：是否有宽带
	 */
	public void setBroadBand(Integer broadBand) {
		this.broadBand = broadBand;
	}
	/**
	 * 获取：是否有宽带
	 */
	public Integer getBroadBand() {
		return broadBand;
	}
	/**
	 * 设置：是否有衣柜
	 */
	public void setWardrobe(Integer wardrobe) {
		this.wardrobe = wardrobe;
	}
	/**
	 * 获取：是否有衣柜
	 */
	public Integer getWardrobe() {
		return wardrobe;
	}
	/**
	 * 设置：
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdatetime() {
		return updatetime;
	}
	/**
	 * 设置：
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：
	 */
	public Integer getStatus() {
		return status;
	}
}
