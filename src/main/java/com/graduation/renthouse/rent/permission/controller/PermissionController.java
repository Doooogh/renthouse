package com.graduation.renthouse.rent.permission.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.graduation.renthouse.rent.common.entity.Tree;
import com.graduation.renthouse.system.utils.PageUtils;
import com.graduation.renthouse.system.utils.Query;
import com.graduation.renthouse.system.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.graduation.renthouse.rent.permission.domain.PermissionDO;
import com.graduation.renthouse.rent.permission.service.PermissionService;

/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-03-11 15:04:00
 */
 
@Controller
@RequestMapping("/permission/permission")
public class PermissionController {
	@Autowired
	private PermissionService permissionService;
	
	@GetMapping()
	@RequiresPermissions("permission:permission:permission")
	String Permission(){
	    return "permission/permission/permission";
	}
	
	@ResponseBody
	@GetMapping("/list")
//	@RequiresPermissions("permission:permission:permission")
	public List<PermissionDO> list(@RequestParam Map<String, Object> params){
		List<PermissionDO> permissionList=permissionService.list(params);
		return permissionList;
	}
	
	@GetMapping("/add/{id}")
	@RequiresPermissions("permission:permission:add")
	String add(@PathVariable("id") Integer id,Model model){
		Map<String,Object> map=new HashMap<>();
		if(id!=-1){
			PermissionDO permission = permissionService.get(id);
			Integer pId=permission.getId();
			String parentName=null;
			if(pId==0){
				parentName="顶级权限";
			}else{
				parentName=permission.getName();
			}
			model.addAttribute("parentName", parentName);
			model.addAttribute("permission", permission);
		}

	List<PermissionDO> perList = permissionService.list(map);
		model.addAttribute("perList",perList);
		model.addAttribute("pid",id);
		return "permission/permission/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("permission:permission:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		PermissionDO permission = permissionService.get(id);
		Integer parentId=permission.getParentId();
		String parentName=null;
		if(parentId==0){
			parentName="顶级权限";
		}else{
			parentName=permissionService.get(parentId).getName();
		}
		model.addAttribute("permission", permission);
		model.addAttribute("parentName", parentName);
	    return "permission/permission/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("permission:permission:add")
	public R save( PermissionDO permission){
		if(permissionService.save(permission)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("permission:permission:edit")
	public R update( PermissionDO permission){
		permissionService.update(permission);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("permission:permission:remove")
	@Transactional
	public R remove(  String id){
		Map<String,Object> map=new HashMap<>();
		map.put("parentId",id);
		List<PermissionDO> list = permissionService.list(map);
		Integer []ids=new Integer[list.size()];
		if(list.size()!=0){

			for (int i = 0; i < list.size(); i++) {
				ids[0]=list.get(i).getId();
			}
			int i = permissionService.batchRemove(ids);
			if(i>0){
				if(permissionService.remove(Integer.valueOf(id))>0){
					return R.ok();
				}else{
					return R.error();
				}
			}else{
				return R.error();
			}
		}
		if(permissionService.remove(Integer.valueOf(id))>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("permission:permission:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		permissionService.batchRemove(ids);
		return R.ok();
	}

	@GetMapping("/tree/{roleId}")
	@ResponseBody
	Tree<PermissionDO> tree(@PathVariable("roleId") Integer roleId) {
		Tree<PermissionDO> tree = permissionService.getTree(roleId);
		System.out.println(tree);
		System.out.println(111);
		return tree;
	}

	@GetMapping("/tree")
	@ResponseBody
	Tree<PermissionDO> tree() {
		Tree<PermissionDO> tree = permissionService.getTree();
		System.out.println(tree);
		return tree;
	}



	
}
