package com.graduation.renthouse.rent.order.controller;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.graduation.renthouse.rent.house.domain.HouseDO;
import com.graduation.renthouse.rent.house.service.HouseService;
import com.graduation.renthouse.rent.landlord.domain.LandlordDO;
import com.graduation.renthouse.rent.landlord.service.LandlordService;

import com.graduation.renthouse.rent.order.domain.Order;
import com.graduation.renthouse.rent.order.domain.OrderDO;
import com.graduation.renthouse.rent.order.domain.OrderVO;
import com.graduation.renthouse.rent.tenant.domain.TenantDO;
import com.graduation.renthouse.rent.tenant.service.TenantService;
import com.graduation.renthouse.system.utils.*;
import jdk.nashorn.internal.codegen.ObjectClassGenerator;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.graduation.renthouse.rent.order.service.OrderService;

/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-03-04 18:51:20
 */
 
@Controller
@RequestMapping("/order/order")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private HouseService houseService;

	@Autowired
	private LandlordService landlordService;

	@Autowired
	private TenantService tenantService;


	@GetMapping()
	@RequiresPermissions("order:order:order")
	String Order(){
	    return "order/order/order";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("order:order:order")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OrderDO> orderList = orderService.list(query);
		Map<String,Object> map=new HashMap<>();
		List<HouseDO> houseList=houseService.list(map);
		List<TenantDO> tenantList=tenantService.list(map);
		List<LandlordDO> landlordList=landlordService.list(map);
		List<Order> orders=new ArrayList<>();
		for (OrderDO orderDO : orderList) {
			Order order=new Order();
			for (HouseDO houseDO : houseList) {
				if(orderDO.getHouseId()==houseDO.getId()){
					for (TenantDO tenantDO : tenantList) {
						if(orderDO.getTenantId()==tenantDO.getId()){
							for (LandlordDO landlordDO : landlordList) {
								if(houseDO.getLandlordId()==landlordDO.getId()){
									BeanUtils.copyProperties(orderDO,order);
									order.setTenantName(tenantDO.getName());
									order.setLandlordName(landlordDO.getName());
									order.setTitle(houseDO.getTitle());
									System.out.println(order.getCreatedate()+"---------------------------");
								/*	order.setCreatedate(DateUtils.parse(order.getCreatedate()));
									order.setCreatedate(DateUtils.parse(order.getUpdatedate()));*/
								}
							}
						}

					}
				}
			}
			orders.add(order);
		}
		int total = orderService.count(query);
		PageUtils pageUtils = new PageUtils(orders, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("order:order:add")
	String add(){
	    return "order/order/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("order:order:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		OrderDO order = orderService.get(id);
		System.out.println(order.getCreatedate()+"--=--==-=----------=-=-=-=-");
		Integer landlordId=order.getLandlordId();
		Integer tenantId=order.getTenantId();
		Integer houseId=order.getHouseId();
		String landlordName = landlordService.get(landlordId).getName();
		String tenantName=tenantService.get(tenantId).getName();
		String title=houseService.get(houseId).getTitle();
		model.addAttribute("order", order);
		model.addAttribute("landlordName",landlordName);
		model.addAttribute("tenantName",tenantName);
		model.addAttribute("title",title);
	    return "order/order/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("order:order:add")
	public R save( OrderDO order){
		if(orderService.save(order)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("order:order:edit")
	@DateTimeFormat(pattern = "yyyy-MM-dd") //入参
	public R update( OrderDO order){
		orderService.update(order);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("order:order:remove")
	public R remove( Integer id){
		if(orderService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("order:order:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		orderService.batchRemove(ids);
		return R.ok();
	}


	@GetMapping("/newOrder/{id}")
	public String newOrder(@PathVariable("id") Integer id,Model model){
		model.addAttribute("houseId",id);
		return "order/order/newOrder";
	}

	@GetMapping("/newOrder1")
	public String newOrder(){
		return "order/order/newOrder";
	}


	@GetMapping("/gain")
	@ResponseBody
	public OrderVO gain(@RequestParam("id") Integer id){
		HouseDO house = houseService.get(id);
		Integer landlordId=house.getLandlordId();
		String landlordName=landlordService.get(landlordId).getName();
		OrderVO order=new OrderVO(id,house.getTitle(),landlordId,landlordName,house.getPrice());
		return order;
	}

	@GetMapping("/gainByTitle")
	@ResponseBody
	public OrderVO gainByTitle(@RequestParam("title") String title){
		OrderVO order=new OrderVO();
		Map<String,Object> map=new HashMap<>();
		map.put("title",title);
		List<HouseDO> list = houseService.list(map);
		if(list.size()==0||list.get(0).getStatus()!=0){
			System.out.println(list.size());
			return order;
		}
		HouseDO house=list.get(0);
		String landlordName=landlordService.get(house.getLandlordId()).getName();
		 order=new OrderVO(house.getId(),house.getTitle(),house.getLandlordId(),landlordName,house.getPrice());
		return order;
	}

	@PostMapping("/ordered")
	@ResponseBody
	public R ordered(String landlordId,String houseId,String tenancyTerm,String price,String tenantName,
					 String sex,String phone){
		Map<String,Object> map=new HashMap<>();
		map.put("name",tenantName);
		List<TenantDO> tenants = tenantService.list(map);
		Integer tenantId=null;
		if(tenants.size()==0){
			TenantDO tenant=new TenantDO();
			tenant.setName(tenantName);
			tenant.setSex(sex);
			tenant.setPhone(phone);
			int a=tenantService.save(tenant);

			if(a>0){
			tenantId=tenantService.list(map).get(0).getId();
			}else{
				return R.error();
			}
		}else{
			TenantDO tenant1=tenantService.list(map).get(0);
			tenantId=tenant1.getId();
			tenant1.setPhone(phone);
			tenant1.setSex(sex);
			int i=tenantService.update(tenant1);
			if(i<=0){
				return R.error();
			}
		}
		String number=RandomUtils.numID();
		HouseDO house2=houseService.get(Integer.valueOf(houseId));
		house2.setStatus(2);
		int a1=houseService.update(house2);
		if(a1<=0){
			return R.error();
		}
		Date date=new Date();
		OrderDO order=new OrderDO(number,Integer.valueOf(landlordId),Integer.valueOf(tenantId),Integer.valueOf(houseId),tenancyTerm,Integer.valueOf(price),2,date,date);
		int a=orderService.save(order);
		if(a>0){
			return R.ok();
		}
		return R.error();
	}



	@PostMapping("/decide")
	@ResponseBody
	public R decide(String landlordId,String houseId,String tenancyTerm,String price,String tenantName,
					String sex,String phone){

		Map<String,Object> map=new HashMap<>();
		map.put("name",tenantName);
		List<TenantDO> tenants = tenantService.list(map);
		Integer tenantId=null;
		if(tenants.size()==0){
			TenantDO tenant=new TenantDO();
			tenant.setName(tenantName);
			tenant.setSex(sex);
			tenant.setPhone(phone);
			int a=tenantService.save(tenant);

			if(a>0){
				tenantId=tenantService.list(map).get(0).getId();
			}else{
				return R.error();
			}
		}else{
			TenantDO tenant1=tenantService.list(map).get(0);
			tenantId=tenant1.getId();
			tenant1.setPhone(phone);
			tenant1.setSex(sex);
			int i=tenantService.update(tenant1);
			if(i<=0){
				return R.error();
			}
		}
		String number=RandomUtils.numID();
		HouseDO house2=houseService.get(Integer.valueOf(houseId));
		house2.setStatus(1);
		int a1=houseService.update(house2);
		if(a1<=0){
			return R.error();
		}
		Date date=new Date();
		OrderDO order=new OrderDO(number,Integer.valueOf(landlordId),Integer.valueOf(tenantId),Integer.valueOf(houseId),tenancyTerm,Integer.valueOf(price),2,date,date);
		int a=orderService.save(order);
		if(a>0){
			return R.ok();
		}
		return R.error();
	}
}
