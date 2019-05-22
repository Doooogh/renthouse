package com.graduation.renthouse.rent.img.controller;

import java.util.List;
import java.util.Map;

import com.graduation.renthouse.system.common.Constant;
import com.graduation.renthouse.system.utils.FileUtil;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.graduation.renthouse.rent.img.domain.ImgDO;
import com.graduation.renthouse.rent.img.service.ImgService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 
 * @author lilong
 * @email 1992lcg@163.com
 * @date 2019-02-26 11:04:54
 */
 
@Controller
@RequestMapping("/img/img")
public class ImgController {
	@Autowired
	private ImgService imgService;
	
	@GetMapping()
	@RequiresPermissions("img:img:img")
	String Img(){
	    return "img/img/img";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("img:img:img")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ImgDO> imgList = imgService.list(query);
		int total = imgService.count(query);
		PageUtils pageUtils = new PageUtils(imgList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("img:img:add")
	String add(){
	    return "img/img/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("img:img:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ImgDO img = imgService.get(id);
		model.addAttribute("img", img);
	    return "img/img/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("img:img:add")
	public R save( ImgDO img){
		if(imgService.save(img)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("img:img:edit")
	public R update( ImgDO img){
		imgService.update(img);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
//	@RequiresPermissions("img:img:remove")
	public R remove( Integer id){
		if(imgService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("img:img:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		imgService.batchRemove(ids);
		return R.ok();
	}

	@PostMapping("/upLoad")
	@ResponseBody
	public R upLoadImg(@RequestParam("file")MultipartFile file,@RequestParam("id") String id){
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		try {
			FileUtil.uploadFile(file.getBytes(),Constant.FILEPATH, fileName);
		}catch (Exception e){
			e.printStackTrace();
			return R.error();
		}
		ImgDO img=new ImgDO();
		img.setSrc(fileName);
		img.setHouseId(Integer.valueOf(id));
		int a=imgService.save(img);
		if(a>0){
			return R.ok();
		}
		return R.error();
	}
	
}
