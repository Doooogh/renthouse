package com.graduation.renthouse.rent.role.controller;

import java.util.List;
import java.util.Map;

import com.graduation.renthouse.system.annotation.Log;
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

import com.graduation.renthouse.rent.role.domain.RoleDO;
import com.graduation.renthouse.rent.role.service.RoleService;
/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-03-11 15:06:07
 */
 
@Controller
@RequestMapping("/role/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@GetMapping()
	@RequiresPermissions("role:role:role")
	String Role(){
	    return "role/role/role";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("role:role:role")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RoleDO> roleList = roleService.list(query);
		int total = roleService.count(query);
		PageUtils pageUtils = new PageUtils(roleList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("role:role:add")
	String add(){
	    return "role/role/add";
	}

	@GetMapping("/edit/{roleId}")
	@RequiresPermissions("role:role:edit")
	String edit(@PathVariable("roleId") Integer roleId,Model model){
		RoleDO role = roleService.get(roleId);
		model.addAttribute("role", role);
	    return "role/role/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("role:role:add")
	public R save( RoleDO role){
		if(roleService.save(role)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@Log("修改角色权限")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("role:role:edit")
	public R update( RoleDO role){
		int a=roleService.update(role);

		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@Log("删除角色")
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("role:role:remove")
	public R remove( Integer roleId){
		if(roleService.remove(roleId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@Log("批量删除角色")
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("role:role:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] roleIds){
		roleService.batchRemove(roleIds);
		return R.ok();
	}
	
}
