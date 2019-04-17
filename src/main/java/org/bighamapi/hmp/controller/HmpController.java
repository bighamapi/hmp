package org.bighamapi.hmp.controller;

import org.bighamapi.hmp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HmpController {
    @Autowired
    private PageInfoService pageInfoService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ColumnService columnService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model){
        Map<String,Object> map = new HashMap<>();

        map.put("pageInfo",pageInfoService.getInfo());
        map.put("articles",articleService.findAll());
        map.put("columns",columnService.findAll());
        map.put("channels",channelService.findAll());
        map.put("user",userService.findByUsername("admin"));
        map.put("CommentMax",articleService.findByVisits(4));
        model.addAllAttributes(map);
        return "index";
    }
    @GetMapping("/article/q/{id}")
    public String article(@PathVariable  String id, Model model){
        model.addAttribute("article",articleService.findById(id));
        model.addAttribute("pageInfo",pageInfoService.getInfo());
        model.addAttribute("user",userService.findByUsername("admin"));
        return "article/_id";
    }
}
