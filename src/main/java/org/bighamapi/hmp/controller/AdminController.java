package org.bighamapi.hmp.controller;

import org.bighamapi.hmp.entity.Result;
import org.bighamapi.hmp.entity.StatusCode;
import org.bighamapi.hmp.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }

    @PostMapping("/layout")
    @ResponseBody
    public Result layout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        LOG.debug("退出后台");
        return new Result(true, StatusCode.OK,"请求成功");
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
        model.addAttribute("columns",columnService.findAll());
        return "admin/template";
    }
    @GetMapping("/articleManage")
    public String articleMange(Model model){
        Map<String,String> html = new HashMap<>();
        html.put("file","admin/articleManage");
        html.put("name","articleManage");
        model.addAttribute("html",html);

        model.addAttribute("pageName","文章管理");
        model.addAttribute("isPublic",true);
        return "admin/template";
    }
    @GetMapping("/tempArticle")
    public String tempArticle(Model model) {

        Map<String, String> html = new HashMap<>();
        html.put("file", "admin/articleManage");
        html.put("name", "articleManage");
        model.addAttribute("html", html);

        model.addAttribute("pageName","草稿箱");
        model.addAttribute("isPublic",false);
        return "admin/template";
    }
    @GetMapping("/comment")
    public String comment(Model model) {
        Map<String, String> html = new HashMap<>();
        html.put("file", "admin/comment");
        html.put("name", "comment");
        model.addAttribute("html", html);

        model.addAttribute("pageName","评论管理");
        return "admin/template";
    }
    @GetMapping("/column")
    public String column(Model model) {
        Map<String, String> html = new HashMap<>();
        html.put("file", "admin/column");
        html.put("name", "column");
        model.addAttribute("html", html);

        model.addAttribute("pageName","专栏管理");
        return "admin/template";
    }
    @GetMapping("/pageManage")
    public String pageInfo(Model model) {
        Map<String, String> html = new HashMap<>();
        html.put("file", "admin/pageManage");
        html.put("name", "pageFrom");
        model.addAttribute("html", html);

        model.addAttribute("pageName","页面设计");
        return "admin/template";
    }

    @GetMapping("/password")
    public String password(Model model) {
        Map<String, String> html = new HashMap<>();
        html.put("file", "admin/password");
        html.put("name", "password");
        model.addAttribute("html", html);

        model.addAttribute("pageName","修改密码");
        model.addAttribute("user",userService.findAdmin());
        return "admin/template";
    }
}
