package com.graduation.renthouse.rent.tag.domain;

import lombok.Data;

@Data
public class TagVO {

    private Integer id;
    //标题描述
    private String description;

    private boolean has;

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHas() {
        return has;
    }

    public void setHas(boolean has) {
        this.has = has;
    }
}
