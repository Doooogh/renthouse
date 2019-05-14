package com.graduation.renthouse.rent.house.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.graduation.renthouse.rent.house.domain.House;
import com.graduation.renthouse.rent.house.domain.HouseVO;
import com.graduation.renthouse.rent.img.domain.ImgDO;
import com.graduation.renthouse.rent.img.service.ImgService;
import com.graduation.renthouse.rent.landlord.domain.LandlordDO;
import com.graduation.renthouse.rent.landlord.domain.LandlordVO;
import com.graduation.renthouse.rent.landlord.service.LandlordService;
import com.graduation.renthouse.rent.tag.domain.TagDO;
import com.graduation.renthouse.rent.tag.service.TagService;
import com.graduation.renthouse.rent.tenant.domain.TenantDO;
import com.graduation.renthouse.rent.tenant.service.TenantService;
import com.graduation.renthouse.system.utils.DateUtils;
import com.graduation.renthouse.system.utils.PageUtils;
import com.graduation.renthouse.system.utils.Query;
import com.graduation.renthouse.system.utils.R;
import jdk.nashorn.internal.codegen.ObjectClassGenerator;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.graduation.renthouse.rent.house.domain.HouseDO;
import com.graduation.renthouse.rent.house.service.HouseService;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-02-22 17:18:56
 */
 
@Controller
@RequestMapping("/house/house")
public class HouseController {

	@Autowired
	private HouseService houseService;
	@Autowired
	private LandlordService landlordService;

	@Autowired
	private TenantService tenantService;

	@Autowired
	private TagService tagService;

	@Autowired
	private ImgService imgService;

	
	@GetMapping()
	@RequiresPermissions("house:house:house")
	String House(){
	    return "house/house/house";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("house:house:house")
	public PageUtils list(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
		System.out.println(params.get("limit").getClass());
	    List<HouseDO> houseList=houseService.list(query);
		//查询列表数据
		List<House> houses=new ArrayList<>();
		String tenantName="";
		String landlordName="";
        for (HouseDO houseDO : houseList) {
            House house=new House();
            try{
				Integer landlordId=houseDO.getLandlordId();
				if(landlordId==null){
					landlordName="";
				}
				landlordName=landlordService.get(landlordId).getName();

            	Integer tenantId=houseDO.getTenantId();
            	if(tenantId==null){
					tenantName="";
				}
				 tenantName=tenantService.get(houseDO.getTenantId()).getName();
			}catch (Exception e){
            	e.printStackTrace();
			}

            BeanUtils.copyProperties(houseDO,house);
            Date date=houseDO.getCreatetime();
            house.setPubdate(DateUtils.getTimeBeforeToDay(date));
            house.setAddTime(DateUtils.format(date));

            house.setLandlordName(landlordName);
            house.setTenantName(tenantName);
            houses.add(house);
        }
		int total = houseService.count(query);
		PageUtils pageUtils = new PageUtils(houses, total);
		return pageUtils;
	}

	@RequestMapping(value = "/houseShow",method ={RequestMethod.GET,RequestMethod.POST},produces ="application/json;charset=UTF-8")
	@ResponseBody
	public PageUtils showHouseVO(@RequestBody String map){
		Map<String,Object> params=new HashMap<>();
		try {
			String str=URLDecoder.decode(map, "UTF-8");
			params = JSON.parseObject(str);
			Set set = params.keySet();
			for (Object o : set) {
				System.out.println(o+",-------"+params.get(o));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	    int total=0;
        Set<String> set = params.keySet();
        for (String s : set) {
            System.out.println(s+",------------"+params.get(s)+"___________________"+s.getClass());
        }
        List<HouseDO> houseList=new ArrayList<>();
        Query query = new Query(params);
        String which= (String) params.get("which");
        System.out.println(which+"----------------------------------------");
        if(StringUtils.isEmpty(which)){
            houseList = houseService.list(query);
            total=houseService.count(query);
        }else if(which.equals("findByMore")){
            houseList = houseService.findByMore(query);
			total=houseService.countByMore(query);
        }else if(which.equals("findByTitle")){
            houseList = houseService.findByTitle(query);
			total=houseService.countByTitle(query);
        }
		System.out.println(houseList.size()+"6666666666666666666666666");
		List<HouseVO> houses=new ArrayList<>();
		Map<String,Object> newMap=new HashMap<>();
		List<TagDO> tags = tagService.list(newMap);
		List<ImgDO> imgs=imgService.list(newMap);
		for (HouseDO houseDO : houseList) {
			HouseVO houseVO=new HouseVO();
			List<TagDO>houseTags=new ArrayList<>();
			List<ImgDO>houseImgs=new ArrayList<>();
			BeanUtils.copyProperties(houseDO,houseVO);
				for (TagDO tag : tags) {
					if(tag.getHouseId()==houseDO.getId()){
						houseTags.add(tag);
					}
				}
				if(!(houseTags.size()==0)){
					houseVO.setTags(houseTags);
				}

			for (ImgDO img : imgs) {
				if(img.getHouseId()==houseDO.getId()){
					houseImgs.add(img);
				}
			}
			if(!(houseImgs.size()==0)){
				houseVO.setImg(houseImgs.get(0).getSrc());
			}
			Date date=houseDO.getCreatetime();
			houseVO.setPubdate(DateUtils.getTimeBeforeToDay(date));
			houses.add(houseVO);
		}

		PageUtils pageUtils=new PageUtils(houses,total);
		return  pageUtils;
	}
	@GetMapping("/add")
	@RequiresPermissions("house:house:add")
	String add(Model model){
		Map <String ,Object>map=new HashMap();
		List<LandlordDO> landlordList=landlordService.list(map);
		List<TenantDO> tenantList=tenantService.list(map);
		model.addAttribute("landlordList",landlordList);
		model.addAttribute("tenantList",tenantList);
	    return "house/house/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("house:house:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		HouseDO house = houseService.get(id);
	/*	String landlordName=landlordService.get(house.getLandlordId()).getName();
		String tenantName=tenantService.get(house.getTenantId()).getName();*/
		Map <String ,Object>map=new HashMap();
		List<LandlordDO> landlordList=landlordService.list(map);
		List<TenantDO> tenantList=tenantService.list(map);
		model.addAttribute("house", house);
		model.addAttribute("landlordList",landlordList);
		model.addAttribute("tenantList",tenantList);
	    return "house/house/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
//	@RequiresPermissions("house:house:add")
	public R save( HouseDO house){
		house.setCreatetime(new Date());
		if(houseService.save(house)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("house:house:edit")
	public R update( HouseDO house){
		house.setUpdatetime(new Date());
		houseService.update(house);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("house:house:remove")
	public R remove( Integer id){
		if(houseService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("house:house:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		houseService.batchRemove(ids);
		return R.ok();
	}

/*	@GetMapping("/showMap/{location}")
	public String showMap(@PathVariable("location") String location, Model model){
		System.out.println(123132);
		System.out.println(location);
		model.addAttribute("location",location);
		return "house/house/housemap";
	}*/

    @GetMapping("/showMap/{location}")
    public String showMap(@PathVariable("location") String location,Model model){
        System.out.println(location);
        model.addAttribute("address",location);
        return "house/house/housemap";
    }

    @GetMapping("/show")
    public String houseShow(){
    	return "house/house/showhouse";
	}

	@GetMapping("/showPic/{id}")
	public String showPic(@PathVariable("id") Integer id,Model model){
    	Map<String,Object> map=new HashMap();
    	map.put("houseId",id);
    	List<ImgDO> imgs=imgService.list(map);
    	model.addAttribute("imgs",imgs);
    	model.addAttribute("houseId",id);
    	return "house/house/showPic";
	}


	@GetMapping("/showDetail/{id}")
	public String showDetail(@PathVariable("id") Integer id,Model model){
    	HouseDO houseDO=houseService.get(id);
    	LandlordDO landlord=landlordService.get(houseDO.getLandlordId());
    	Map <String,Object> map=new HashMap<>();
    	map.put("houseId",houseDO.getId());
    	List<TagDO>tags=tagService.list(map);
    	List<ImgDO> imgs=imgService.list(map);
    	String landlordName=null;
    	if(landlord.getSex().equals("男")){
    		landlordName=landlord.getName().substring(0,1)+"先生";
		}else{
			landlordName=landlord.getName().substring(0,1)+"女士";
		}
		Date creatTime=houseDO.getCreatetime();
		String pubdate = DateUtils.getTimeBeforeToDay(creatTime);
		String time=DateUtils.format(creatTime);
    	model.addAttribute("house",houseDO);
    	model.addAttribute("landlordName",landlordName);
    	model.addAttribute("pubdate",pubdate);
    	model.addAttribute("tags",tags);
    	model.addAttribute("time",time);
    	model.addAttribute("imgs",imgs);
    	return "house/house/showDetail";
	}


	@GetMapping("/get")
	@ResponseBody
	public HouseDO get(Integer id){
		HouseDO house = houseService.get(id);
		return house;
	}
}
