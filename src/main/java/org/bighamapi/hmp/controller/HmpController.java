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
    @Autowired
    private CommentService commentService;
    private Map<String,Object> getMap(){
        Map<String,Object> map = new HashMap<>();

        map.put("pageInfo",pageInfoService.getInfo());
        map.put("articles",articleService.findAll());
        map.put("columns",columnService.findAll());
        map.put("channels",channelService.findAll());
        map.put("user",userService.findByUsername("admin"));
        map.put("CommentMax",articleService.findByVisits(4));
        return map;
    }

    /**
     * 首页
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model){
        Map map = getMap();
        Map<String,String> html = new HashMap<>();
        html.put("file","index");
        html.put("name","index");
        map.put("html", html);
        model.addAllAttributes(map);
        return "template";
    }

    /**
     * 标签页
     * @param model
     * @return
     */
    @GetMapping("/label")
    public String label(Model model){
        Map map = getMap();
        Map<String,String> html = new HashMap<>();
        html.put("file","label/label");
        html.put("name","label");
        map.put("html", html);
        model.addAllAttributes(map);
        return "template";
    }
    @GetMapping("/archives")
    public String groupByDate(Model model){
        Map map = getMap();
        Map<String,String> html = new HashMap<>();
        html.put("file","label/list");
        html.put("name","list");
        map.put("html", html);
        model.addAllAttributes(map);
        model.addAttribute("articleByData",articleService.groupByDate());
        return "template";
    }

    /**
     * 文章正文页
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/article/q/{id}")
    public String article(@PathVariable  String id, Model model){
        model.addAttribute("article",articleService.findById(id));
        model.addAttribute("pageInfo",pageInfoService.getInfo());
        model.addAttribute("comments",commentService.findByArticleId(id));
        return "article/_id";
    }

    /**
     * 根据标签的文章列表
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/channel/q/{id}")
    public String channel(@PathVariable  String id, Model model){

        model.addAttribute("articles",channelService.findById(id).getArticle());
        model.addAttribute("pageInfo",pageInfoService.getInfo());

        model.addAllAttributes(getMap());
        return "index";
    }

    /**
     * 根据专栏的文章列表
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/column/q/{id}")
    public String column(@PathVariable  String id, Model model){
        model.addAllAttributes(getMap());
        return "index";
    }

}
