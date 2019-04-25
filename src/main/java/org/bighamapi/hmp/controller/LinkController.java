package org.bighamapi.hmp.controller;

import org.bighamapi.hmp.entity.PageResult;
import org.bighamapi.hmp.entity.Result;
import org.bighamapi.hmp.entity.StatusCode;
import org.bighamapi.hmp.pojo.Column;
import org.bighamapi.hmp.pojo.Link;
import org.bighamapi.hmp.service.ColumnService;
import org.bighamapi.hmp.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 专栏 控制器层
 * @author bighamapi
 *
 */
@RestController
@RequestMapping("/link")
public class LinkController {

	@Autowired
	private LinkService linkService;
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功",linkService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",linkService.findById(id));
	}
	
	/**
	 * 增加
	 * @param link
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Link link){
		linkService.save(link);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param link
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Link link, @PathVariable String id ){
		link.setId(id);
		linkService.update(link);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		linkService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
