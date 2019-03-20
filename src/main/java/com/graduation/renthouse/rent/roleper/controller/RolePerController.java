package com.graduation.renthouse.rent.roleper.controller;

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

import com.graduation.renthouse.rent.roleper.domain.RolePerDO;
import com.graduation.renthouse.rent.roleper.service.RolePerService;


/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-03-13 15:54:55
 */
 
@Controller
@RequestMapping("/roleper/rolePer")
public class RolePerController {
	@Autowired
	private RolePerService rolePerService;
	
	@GetMapping()
	@RequiresPermissions("roleper:rolePer:rolePer")
	String RolePer(){
	    return "roleper/rolePer/rolePer";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("roleper:rolePer:rolePer")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RolePerDO> rolePerList = rolePerService.list(query);
		int total = rolePerService.count(query);
		PageUtils pageUtils = new PageUtils(rolePerList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("roleper:rolePer:add")
	String add(){
	    return "roleper/rolePer/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("roleper:rolePer:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		RolePerDO rolePer = rolePerService.get(id);
		model.addAttribute("rolePer", rolePer);
	    return "roleper/rolePer/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("roleper:rolePer:add")
	public R save( RolePerDO rolePer){
		if(rolePerService.save(rolePer)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("roleper:rolePer:edit")
	public R update( RolePerDO rolePer){
		rolePerService.update(rolePer);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("roleper:rolePer:remove")
	public R remove( Integer id){
		if(rolePerService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("roleper:rolePer:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		rolePerService.batchRemove(ids);
		return R.ok();
	}
	
}
