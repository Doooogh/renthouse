package com.graduation.renthouse.rent.house.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class House implements Serializable {
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
    //房东姓名
    private String landlordName;
    //租客姓名
    private String tenantName;
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
    //
    private Date createtime;
    //
    private Date updatetime;
    //
    private Integer status;


}
