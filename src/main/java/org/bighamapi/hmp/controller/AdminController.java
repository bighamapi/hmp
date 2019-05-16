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
    @Autowired
    private LinkService linkService;

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
    //主页
    @GetMapping
    public String admin(Model model){
        model.addAttribute("articles",articleService.findByVisits(3));
        return "admin/template";
    }
    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("articles",articleService.findByVisits(3));
        return "admin/index";
    }
    //文章发布和更新
    @RequestMapping(value = {"/article/{id}", "/article"},method = RequestMethod.GET)
    public String article(@PathVariable(required = false) String id, Model model){
        if(id !=null){
            model.addAttribute("article",articleService.findById(id));
        }
        model.addAttribute("columns",columnService.findAll());
        return "admin/article";
    }
    //文章管理
    @GetMapping("/articleManage")
    public String articleMange(Model model){
        model.addAttribute("pageName","文章管理");
        model.addAttribute("isPublic",true);
        return "admin/articleManage";
    }
    //草稿箱
    @GetMapping("/tempArticle")
    public String tempArticle(Model model) {
        model.addAttribute("pageName","草稿箱");
        model.addAttribute("isPublic",false);
        return "admin/articleManage";
    }

    //评论管理
    @GetMapping("/comment")
    public String comment() {
        return "admin/comment";
    }
    //专栏管理
    @GetMapping("/column")
    public String column() {
        return "admin/column";
    }
    //页面设计
    @GetMapping("/pageManage")
    public String pageInfo() {
        return "admin/pageManage";
    }
    //密码管理
    @GetMapping("/password")
    public String password(Model model) {
        model.addAttribute("user",userService.findAdmin());
        return "admin/password";
    }
    //链接管理
    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("link",linkService.findAll());
        return "admin/link";
    }
}
