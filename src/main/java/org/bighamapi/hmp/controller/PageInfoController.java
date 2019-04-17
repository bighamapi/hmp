package org.bighamapi.hmp.controller;

import org.bighamapi.hmp.dto.PageInfo;
import org.bighamapi.hmp.entity.Result;
import org.bighamapi.hmp.entity.StatusCode;
import org.bighamapi.hmp.pojo.User;
import org.bighamapi.hmp.service.PageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/pageInfo")
public class PageInfoController {

    @Autowired
    private PageInfoService pageInfoService;


    @GetMapping("/{id}")
    public Result findById(@PathVariable  int id){
        PageInfo pageInfo = pageInfoService.findById(id);
        return new Result(true, StatusCode.OK, "请求成功",pageInfo);
    }
    @GetMapping
    public Result getInfo(){
        return new Result(true, StatusCode.OK, "请求成功",pageInfoService.getInfo());
    }

    /**
     * 修改页面信息
     * @return
     */
    @PutMapping
    public Result update(@RequestBody PageInfo pageInfo){
        pageInfoService.update(pageInfo);
        return new Result(true, StatusCode.OK , "请求成功");
    }

}
