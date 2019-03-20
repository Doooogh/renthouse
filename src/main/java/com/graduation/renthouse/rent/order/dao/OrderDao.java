package com.graduation.renthouse.rent.order.dao;

import java.util.List;
import java.util.Map;

import com.graduation.renthouse.rent.order.domain.Order;
import com.graduation.renthouse.rent.order.domain.OrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-03-04 18:51:20
 */
@Mapper
public interface OrderDao {

	OrderDO get(Integer id);
	
	List<OrderDO> list(Map<String, Object> map);

	List<Order> listMap();

	int count(Map<String, Object> map);
	
	int save(OrderDO order);
	
	int update(OrderDO order);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
