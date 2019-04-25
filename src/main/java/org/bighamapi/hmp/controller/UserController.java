package org.bighamapi.hmp.controller;

import org.bighamapi.hmp.entity.Result;
import org.bighamapi.hmp.entity.StatusCode;
import org.bighamapi.hmp.pojo.User;
import org.bighamapi.hmp.service.UserService;
import org.bighamapi.hmp.util.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private Logger LOG = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private IdWorker idWorker;

    @GetMapping("/{id}")
    public Result findById(@PathVariable  String id){
        User user = userService.findById(id);
        return new Result(true, StatusCode.OK, "请求成功",user);
    }
    @GetMapping("/info")
    public Result getInfo(){
        return new Result(true, StatusCode.OK, "请求成功",userService.findAdmin());
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpServletRequest request){
        User user1 = userService.findByUsername(user.getUsername());
        if(user1==null){
            LOG.error("用户名密码错误： 登陆失败");
            return new Result(false, StatusCode.LOGINERROR , "用户名密码错误");
        }
        if (!user1.getPassword().equals(user.getPassword())){
            LOG.error("用户名密码错误： 登陆失败");
            return new Result(false, StatusCode.LOGINERROR , "用户名密码错误");
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user1);
        LOG.info("登陆成功");
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
        User admin = userService.findAdmin();

        if (!admin.getId().equals(id))
            return new Result(false,StatusCode.ERROR,"未知错误");

        admin.setPassword(user.getPassword());
        userService.update(admin);
        return new Result(true, StatusCode.OK , "请求成功");
    }

}
