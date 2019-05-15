package com.graduation.renthouse.rent.insertlog.controller;

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

import com.graduation.renthouse.rent.insertlog.domain.InsertLogDO;
import com.graduation.renthouse.rent.insertlog.service.InsertLogService;


/**
 * 
 * 
 * @author Li
 * @email 1083460362@qq.com
 * @date 2019-05-08 23:55:35
 */
 
@Controller
@RequestMapping("/insertlog/insertLog")
public class InsertLogController {
	@Autowired
	private InsertLogService insertLogService;
	
	@GetMapping()
	@RequiresPermissions("insertlog:insertLog:insertLog")
	String InsertLog(){
	    return "insertlog/insertLog/insertLog";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("insertlog:insertLog:insertLog")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<InsertLogDO> insertLogList = insertLogService.list(query);
		int total = insertLogService.count(query);
		PageUtils pageUtils = new PageUtils(insertLogList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("insertlog:insertLog:add")
	String add(){
	    return "insertlog/insertLog/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("insertlog:insertLog:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		InsertLogDO insertLog = insertLogService.get(id);
		model.addAttribute("insertLog", insertLog);
	    return "insertlog/insertLog/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("insertlog:insertLog:add")
	public R save(InsertLogDO insertLog){
		if(insertLogService.save(insertLog)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("insertlog:insertLog:edit")
	public R update( InsertLogDO insertLog){
		insertLogService.update(insertLog);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("insertlog:insertLog:remove")
	public R remove( Integer id){
		if(insertLogService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("insertlog:insertLog:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		insertLogService.batchRemove(ids);
		return R.ok();
	}
	
}
