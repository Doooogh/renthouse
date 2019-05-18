package com.graduation.renthouse.system.job.task;

import com.graduation.renthouse.rent.house.service.HouseExService;
import com.graduation.renthouse.rent.house.service.HouseService;
import com.graduation.renthouse.rent.order.service.OrderExService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class HouseJob implements Job {

    @Autowired
    private HouseExService houseExService;

    @Autowired
    private OrderExService orderExService;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        houseExService.houseJobSetStatus();
        orderExService.orderJobSetStatus();
    }
}
