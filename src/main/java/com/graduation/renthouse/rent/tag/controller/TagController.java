package com.graduation.renthouse.rent.tag.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.graduation.renthouse.rent.tag.domain.TagHouse;
import com.graduation.renthouse.rent.tag.domain.TagVO;
import com.graduation.renthouse.rent.tag.service.TagHouseService;
import com.graduation.renthouse.system.utils.PageUtils;
import com.graduation.renthouse.system.utils.Query;
import com.graduation.renthouse.system.utils.R;
import jdk.nashorn.internal.codegen.ObjectClassGenerator;
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

import com.graduation.renthouse.rent.tag.domain.TagDO;
import com.graduation.renthouse.rent.tag.service.TagService;

/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-02-26 11:08:07
 */
 
@Controller
@RequestMapping("/tag/tag")
public class TagController {
	@Autowired
	private TagService tagService;

	@Autowired
	private TagHouseService tagHouseService;
	
	@GetMapping()
	@RequiresPermissions("tag:tag:tag")
	String Tag(){
	    return "tag/tag/tag";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("tag:tag:tag")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TagDO> tagList = tagService.list(query);
		int total = tagService.count(query);
		PageUtils pageUtils = new PageUtils(tagList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("tag:tag:add")
	String add(){
	    return "tag/tag/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("tag:tag:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TagDO tag = tagService.get(id);
		model.addAttribute("tag", tag);
	    return "tag/tag/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("tag:tag:add")
	public R save( TagDO tag){
		if(tagService.save(tag)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tag:tag:edit")
	public R update( TagDO tag){
		tagService.update(tag);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("tag:tag:remove")
	public R remove( Integer id){
		if(tagService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("tag:tag:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		tagService.batchRemove(ids);
		return R.ok();
	}

	@GetMapping("/showTag/{houseId}")
	public String showTag(@PathVariable("houseId") Integer houseId,Model model) throws Exception {
		Map<String,Object> map=new HashMap();
		List<TagDO> allTag = tagService.list(map);
		map.clear();
		if(houseId==null){
			throw  new Exception("传递参数为null");
		}
			map.put("houseId",houseId);
			List<TagHouse> houseTags = tagHouseService.list(map);
			List<TagVO> tagVOS=new ArrayList<>();
		for (TagDO tagDO : allTag) {
			TagVO tagVO=new TagVO();
			tagVO.setId(tagDO.getId());
			tagVO.setDescription(tagDO.getDescription());
			if(houseTags.size()!=0){
				for (TagHouse houseTag : houseTags) {
					if(houseTag.getTagId()==tagDO.getId()){
						tagVO.setHas(true);
						break;
					}
				}
			}
			tagVOS.add(tagVO);
		}
			model.addAttribute("allTag",tagVOS);



		return "/tag/tag/selecttag";
	}

	@PostMapping("/changeTags")
	@ResponseBody
	@Transactional
	public R changeTags(Integer[] tagIds,Integer houseId ) throws Exception {

		List<Integer> tagHouseIds=tagHouseService.getTagIds(houseId);
		tagHouseService.batchRemove(tagHouseIds);

		List<TagHouse> tagHouses=new ArrayList<>();

		for (Integer tagId : tagIds) {
			TagHouse tagHouse=new TagHouse();
			tagHouse.setHouseId(houseId);
			tagHouse.setTagId(tagId);
			tagHouses.add(tagHouse);
		}
		int a=tagHouseService.batchSave(tagHouses);
		if(a>0){
			return R.ok();
		}
		return R.error();
	}
	
}
