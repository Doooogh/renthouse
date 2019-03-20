package com.graduation.renthouse.rent.index.entity;

import lombok.Data;

@Data
public class Info {
    private String name;

    private Integer num;

    public Info() {
    }

    public Info(String name, Integer num) {
        this.name = name;
        this.num = num;
    }
}
