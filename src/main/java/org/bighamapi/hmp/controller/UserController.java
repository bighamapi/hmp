package org.bighamapi.hmp.controller;

import org.bighamapi.hmp.entity.Result;
import org.bighamapi.hmp.pojo.User;
import org.bighamapi.hmp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Result findById(@PathVariable  String id){
        User user = userService.findById(id);
        return new Result();
    }

    @PostMapping("/login")
    public Result login(User user){


        return new Result();
    }
    @PostMapping()
    public Result add(User user){
        userService.save(user);
        return new Result();
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
        return new Result();
    }

}
