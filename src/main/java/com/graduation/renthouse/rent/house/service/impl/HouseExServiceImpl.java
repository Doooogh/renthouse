package com.graduation.renthouse.rent.house.service.impl;

import com.graduation.renthouse.rent.house.dao.HouseExDao;
import com.graduation.renthouse.rent.house.service.HouseExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HouseExServiceImpl implements HouseExService {

    @Autowired
    private HouseExDao houseExDao;

    @Override
    public int houseJobSetStatus() {
        return houseExDao.houseJobSetStatus();
    }
}
