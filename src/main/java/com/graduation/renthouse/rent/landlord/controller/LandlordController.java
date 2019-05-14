package com.graduation.renthouse.rent.landlord.controller;

import java.util.List;
import java.util.Map;

import com.graduation.renthouse.rent.house.service.HouseService;
import com.graduation.renthouse.rent.landlord.domain.LandlordVO;
import com.graduation.renthouse.system.annotation.InsertLog;
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

import com.graduation.renthouse.rent.landlord.domain.LandlordDO;
import com.graduation.renthouse.rent.landlord.service.LandlordService;


/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-02-22 17:01:39
 */
 
@Controller
@RequestMapping("/landlord/landlord")
public class LandlordController {
	@Autowired
	private LandlordService landlordService;

	@Autowired
	private HouseService houseService;
	
	@GetMapping()
	@RequiresPermissions("landlord:landlord:landlord")
	String Landlord(){
	    return "landlord/landlord/landlord";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("landlord:landlord:landlord")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LandlordDO> landlordList = landlordService.list(query);
		int total = landlordService.count(query);
		PageUtils pageUtils = new PageUtils(landlordList, total);
		return pageUtils;
	}

	//查询房东旗下房产
	@ResponseBody
	@GetMapping("/listByLandlord")
//	@RequiresPermissions("landlord:landlord:landlord")
	public PageUtils listByLandlord(@RequestParam Map<String, Object> params){
		PageUtils pageUtils = landlordService.listByLandlord(params);
		return pageUtils;
	}

	@GetMapping("/landlordHouse")
	public String landlordHouse(){
		return "/landlordhouses/lanlordhouses/lanlordhouses";
	}

	@ResponseBody
	@GetMapping("/getById")
	public LandlordDO getById(Integer id){
		return landlordService.get(id);
	}

	@GetMapping("/details/{landlordId}")
	public String look(@PathVariable("landlordId") Integer landlordId,Model model){
	  /*  PageUtils house=
        LandlordDO landlordDO = landlordService.get(landlordId);
        model.addAttribute("landlord",landlordDO);*/
        return "landlord/landlord/details";
    }
	@GetMapping("/add")
	@RequiresPermissions("landlord:landlord:add")
	String add(){
	    return "landlord/landlord/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("landlord:landlord:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		LandlordDO landlord = landlordService.get(id);
		model.addAttribute("landlord", landlord);
	    return "landlord/landlord/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("landlord:landlord:add")
	@InsertLog("landlord")
	public R save( LandlordDO landlord){
		if(landlordService.save(landlord)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("landlord:landlord:edit")
	public R update( LandlordDO landlord){
		landlordService.update(landlord);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("landlord:landlord:remove")
	public R remove( Integer id){
		if(landlordService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("landlord:landlord:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		landlordService.batchRemove(ids);
		return R.ok();
	}
	
}
