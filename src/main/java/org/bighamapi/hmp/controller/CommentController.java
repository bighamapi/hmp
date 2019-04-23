package org.bighamapi.hmp.controller;


import org.bighamapi.hmp.entity.PageResult;
import org.bighamapi.hmp.entity.Result;
import org.bighamapi.hmp.entity.StatusCode;
import org.bighamapi.hmp.pojo.Comment;
import org.bighamapi.hmp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @PostMapping
    public Result add(@RequestBody Comment comment){
        commentService.add(comment);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id){
        commentService.deleteById(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }
    /**
     * 分页+多条件查询
     * @param searchMap 查询条件封装
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
    public Result findSearch(@RequestBody(required=false) Map searchMap , @PathVariable int page, @PathVariable int size){
        Page<Comment> pageList = commentService.findSearch(searchMap, page, size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Comment>(pageList.getTotalElements(), pageList.getContent()) );
    }
}
