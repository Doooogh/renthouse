package com.graduation.renthouse.rent.order.service;

import com.graduation.renthouse.rent.order.dao.OrderExDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderExServiceImpl implements OrderExService {

    @Autowired
    private OrderExDao  orderExDao;

    @Override
    public int orderJobSetStatus() {
        return orderExDao.OrderJobSetStatus();
    }
}
