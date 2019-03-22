package org.bighamapi.hmp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("data", "恭喜，Spring boot集成 Thymeleaf成功！");
        return "index";
    }
}
