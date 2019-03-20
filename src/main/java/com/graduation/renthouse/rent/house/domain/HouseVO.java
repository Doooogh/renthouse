package com.graduation.renthouse.rent.house.domain;

import com.graduation.renthouse.rent.tag.domain.TagDO;
import lombok.Data;

import java.util.List;

@Data
public class HouseVO {
    private Integer id;
    private String img;
    private String title;
    private String largeAreas;
    private String smallAreas;
    private String squareMeter;
    private String orientation;
    private String houseType;
    private String pubdate;
    private Integer price;
    private List<TagDO> tags;

}
