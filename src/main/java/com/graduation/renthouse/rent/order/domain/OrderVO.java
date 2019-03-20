package com.graduation.renthouse.rent.order.domain;

import lombok.Data;

@Data
public class OrderVO {
     private Integer houseId;
     private String title;
     private Integer landlordId;
     private String landlordName;
     private Integer price;

    public OrderVO() {
    }

    public OrderVO(Integer houseId, String title, Integer landlordId, String landlordName, Integer price) {
        this.houseId = houseId;
        this.title = title;
        this.landlordId = landlordId;
        this.landlordName = landlordName;
        this.price = price;
    }
}
