package org.bighamapi.hmp.controller;

import org.bighamapi.hmp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/admin")
public class AdminController {
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
    @Autowired
    private CommentService commentService;

    public String login(){

        return "";
    }
    @GetMapping
    public String admin(Model model){
        Map<String,String> html = new HashMap<>();
        html.put("file","admin/index");
        html.put("name","index");
        model.addAttribute("html",html);
        return "admin/template";
    }
    @RequestMapping(value = {"/article/{id}", "/article"},method = RequestMethod.GET)
    public String article(@PathVariable(required = false) String id, Model model){
        if(id !=null){
            model.addAttribute("article",articleService.findById(id));
        }
        Map<String,String> html = new HashMap<>();
        html.put("file","admin/article");
        html.put("name","article");
        model.addAttribute("html",html);
        model.addAttribute("pageName","文章发布");
        return "admin/template";
    }
    @GetMapping("/articleManage")
    public String articleMange(Model model){
        Map<String,String> html = new HashMap<>();
        html.put("file","admin/articleManage");
        html.put("name","articleManage");
        model.addAttribute("html",html);
        model.addAttribute("pageName","文章管理");
        return "admin/template";
    }

}
