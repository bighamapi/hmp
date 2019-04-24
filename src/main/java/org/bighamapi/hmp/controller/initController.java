package org.bighamapi.hmp.controller;

import org.bighamapi.hmp.pojo.User;
import org.bighamapi.hmp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class initController {

    @Autowired
    UserService userService;
    /**
     * 初始化
     */
    @GetMapping("/init")
    public String init(){
//        User user = new User();
//        user.setId("admin");
//        user.setRole("admin");
//        user.setUsername("admin");
//        user.setPassword("admin");
//        user.setImage("https://github.com/identicons/b9485e300abe165e5b290960a0327b94.png");
//        userService.save(user);这些会在html中提交
        return "admin/init";
    }
}
