package com.graduation.renthouse.rent.tenant.controller;

import java.util.List;
import java.util.Map;

import com.graduation.renthouse.system.utils.PageUtils;
import com.graduation.renthouse.system.utils.Query;
import com.graduation.renthouse.system.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.graduation.renthouse.rent.tenant.domain.TenantDO;
import com.graduation.renthouse.rent.tenant.service.TenantService;
/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-02-23 10:43:10
 */
 
@Controller
@RequestMapping("/tenant/tenant")
public class TenantController {
	@Autowired
	private TenantService tenantService;
	
	@GetMapping()
	@RequiresPermissions("tenant:tenant:tenant")
	String Tenant(){
	    return "tenant/tenant/tenant";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("tenant:tenant:tenant")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TenantDO> tenantList = tenantService.list(query);
		int total = tenantService.count(query);
		PageUtils pageUtils = new PageUtils(tenantList, total);
		return pageUtils;
	}
	@ResponseBody
	@GetMapping("/getById")
	public TenantDO getById(Integer id){
		return tenantService.get(id);
	}
	
	@GetMapping("/add")
	@RequiresPermissions("tenant:tenant:add")
	String add(){
	    return "tenant/tenant/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("tenant:tenant:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TenantDO tenant = tenantService.get(id);
		model.addAttribute("tenant", tenant);
	    return "tenant/tenant/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("tenant:tenant:add")
	public R save(TenantDO tenant){
		if(tenantService.save(tenant)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tenant:tenant:edit")
	public R update( TenantDO tenant){
		tenantService.update(tenant);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("tenant:tenant:remove")
	public R remove( Integer id){
		if(tenantService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("tenant:tenant:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		tenantService.batchRemove(ids);
		return R.ok();
	}
	
}
