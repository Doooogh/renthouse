package com.graduation.renthouse.rent.landlord.domain;

import com.graduation.renthouse.rent.house.domain.HouseDO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LandlordVO  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private List<HouseDO> houses;
}
