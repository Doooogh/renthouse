package com.graduation.renthouse.rent.order.service.impl;

import com.graduation.renthouse.rent.order.domain.OrderDO;
import com.graduation.renthouse.system.annotation.InsertLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.graduation.renthouse.rent.order.dao.OrderDao;
import com.graduation.renthouse.rent.order.service.OrderService;



@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public OrderDO get(Integer id){
		return orderDao.get(id);
	}
	
	@Override
	public List<OrderDO> list(Map<String, Object> map){
		return orderDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderDao.count(map);
	}
	
	@Override
	@InsertLog("order")
	public int save(OrderDO order){
		return orderDao.save(order);
	}
	
	@Override
	public int update(OrderDO order){
		return orderDao.update(order);
	}
	
	@Override
	public int remove(Integer id){
		return orderDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return orderDao.batchRemove(ids);
	}
	
}
