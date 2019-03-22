package org.bighamapi.hmp.controller;

import org.bighamapi.hmp.entity.Result;
import org.bighamapi.hmp.entity.StatusCode;
import org.bighamapi.hmp.pojo.User;
import org.bighamapi.hmp.service.UserService;
import org.bighamapi.hmp.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private IdWorker idWorker;

    @GetMapping("/{id}")
    public Result findById(@PathVariable  String id){
        User user = userService.findById(id);
        return new Result(true, StatusCode.OK, "请求成功",user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User user1 = userService.findByUsername(user.getUsername());
        if(user1==null){
            return new Result(false, StatusCode.LOGINERROR , "用户名密码错误");
        }
        if (!user1.getPassword().equals(user.getPassword())){
            return new Result(false, StatusCode.LOGINERROR , "用户名密码错误");
        }
        return new Result(true, StatusCode.OK , "请求成功");
    }
    @PostMapping()
    public Result add(@RequestBody User user){
        user.setId(idWorker.nextId()+"");
        userService.save(user);
        return new Result(true, StatusCode.OK , "请求成功");
    }
    /**
     * 修改用户密码
     * @param user
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public Result update(User user,@PathVariable String id){
        user.setId(id);
        userService.update(user);
        return new Result(true, StatusCode.OK , "请求成功");
    }

}
