package com.graduation.renthouse.rent.userrole.controller;

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

import com.graduation.renthouse.rent.userrole.domain.UserRoleDO;
import com.graduation.renthouse.rent.userrole.service.UserRoleService;

/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-03-13 16:12:50
 */
 
@Controller
@RequestMapping("/userrole/userRole")
public class UserRoleController {
	@Autowired
	private UserRoleService userRoleService;
	
	@GetMapping()
	@RequiresPermissions("userrole:userRole:userRole")
	String UserRole(){
	    return "userrole/userRole/userRole";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("userrole:userRole:userRole")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UserRoleDO> userRoleList = userRoleService.list(query);
		int total = userRoleService.count(query);
		PageUtils pageUtils = new PageUtils(userRoleList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("userrole:userRole:add")
	String add(){
	    return "userrole/userRole/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("userrole:userRole:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		UserRoleDO userRole = userRoleService.get(id);
		model.addAttribute("userRole", userRole);
	    return "userrole/userRole/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("userrole:userRole:add")
	public R save( UserRoleDO userRole){
		if(userRoleService.save(userRole)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("userrole:userRole:edit")
	public R update( UserRoleDO userRole){
		userRoleService.update(userRole);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("userrole:userRole:remove")
	public R remove( Integer id){
		if(userRoleService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("userrole:userRole:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		userRoleService.batchRemove(ids);
		return R.ok();
	}
	
}
