package com.graduation.renthouse.rent.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.graduation.renthouse.system.utils.DateUtils;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Order {


    private Integer id;
    private String number;
    private String landlordName;
    private String tenantName;
    private String title;
    private String tenancyTerm;
    private Integer price;
    private Integer status;
    @JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss" ,  timezone="GMT+8")
    private Date createdate;
    @JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss" ,  timezone="GMT+8")
    private Date updatedate;



    public Order() {
    }

    public Order(Integer id, String number, String landlordName, String tenantName, String title, String tenancyTerm, Integer price, Integer status, Date createdate, Date updatedate) {
        this.id = id;
        this.number = number;
        this.landlordName = landlordName;
        this.tenantName = tenantName;
        this.title = title;
        this.tenancyTerm = tenancyTerm;
        this.price = price;
        this.status = status;
        this.createdate = createdate;
        this.updatedate = updatedate;
    }
}
