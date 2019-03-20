package com.graduation.renthouse.rent.user.controller;

import java.util.*;

import com.graduation.renthouse.rent.role.domain.RoleDO;
import com.graduation.renthouse.rent.role.service.RoleService;
import com.graduation.renthouse.rent.userrole.domain.UserRoleDO;
import com.graduation.renthouse.rent.userrole.service.UserRoleService;
import com.graduation.renthouse.system.utils.PageUtils;
import com.graduation.renthouse.system.utils.Query;
import com.graduation.renthouse.system.utils.R;
import jdk.nashorn.internal.codegen.ObjectClassGenerator;
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

import com.graduation.renthouse.rent.user.domain.UserDO;
import com.graduation.renthouse.rent.user.service.UserService;

/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-03-11 15:01:29
 */
 
@Controller
@RequestMapping("/user/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleService userRoleService;
	
	@GetMapping()
	@RequiresPermissions("user:user:user")
	String User(){
	    return "user/user/user";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("user:user:user")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UserDO> userList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtils = new PageUtils(userList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("user:user:add")
	String add(Model model){
		Map<String,Object> map=new HashMap<>();
		List<RoleDO> roles=roleService.list(map);
		model.addAttribute("roles",roles);
		for (RoleDO role : roles) {
			System.out.println(role.getName());
		}
	    return "user/user/add";
	}

	@GetMapping("/edit/{userId}")
	@RequiresPermissions("user:user:edit")
	String edit(@PathVariable("userId") Integer userId,Model model){
		UserDO user = userService.get(userId);
		List<Integer> roleIds = userRoleService.selectRoleIdByUserId(userId);
		List<RoleDO> roles=roleService.list(new HashMap<>());
		model.addAttribute("user", user);
		model.addAttribute("roles", roles);
		model.addAttribute("roleIds", roleIds);
	    return "user/user/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("user:user:add")
	public R save(UserDO user){
		for (Integer integer : user.getRoleIds()) {
			System.out.println(integer+"--------------------");
		}
		user.setCreatetime(new Date());
		System.out.println(user.getCreatetime());
		if(userService.save(user)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("user:user:edit")
	public R update( UserDO user){
		userService.update(user);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("user:user:remove")
	public R remove( Integer userId){
		if(userService.remove(userId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("user:user:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] userIds){
		userService.batchRemove(userIds);
		return R.ok();
	}

	@PostMapping("/selectByUsername")
	@ResponseBody
	public boolean selectByUsername(@RequestParam Map<String, Object> params){
		String username= (String) params.get("username");
		System.out.println(username+"----------------");
		List<UserDO> users = userService.selectByUsername(username);
		System.out.println(users.size());
		int size=users.size();
		if(size<=0){
			return true;
		}
		return false;
	}

}
