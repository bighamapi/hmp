package org.bighamapi.hmp.controller;


import org.bighamapi.hmp.entity.Result;
import org.bighamapi.hmp.entity.StatusCode;
import org.bighamapi.hmp.pojo.Comment;
import org.bighamapi.hmp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
