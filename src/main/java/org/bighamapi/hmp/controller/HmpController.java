package org.bighamapi.hmp.controller;

import org.bighamapi.hmp.pojo.Article;
import org.bighamapi.hmp.service.*;
import org.bighamapi.hmp.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private LinkService linkService;

    @Autowired
    CacheManager cacheManager;//缓存管理器

    private Map<String,Object> getMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("articleTotal",articleService.count());
        map.put("commentTotal",commentService.count());
        List<Article> all = articleService.findAll();
        int sum =0;
        for (Article article:all) {
            sum +=article.getVisits();
        }
        map.put("visitsTotal",sum);
        map.put("pageInfo",pageInfoService.getInfo());
        map.put("columns",columnService.findAll());
        map.put("channels",channelService.findAll());
        map.put("user",userService.findAdmin());
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
        map.put("articles",articleService.findAll());
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

    /**
     * 存档
     * @param model
     * @return
     */
    @GetMapping("/archives")
    public String groupByDate(Model model){
        Map map = getMap();
        Map<String,String> html = new HashMap<>();
        html.put("file","label/list");
        html.put("name","list");
        map.put("html", html);
        model.addAttribute("articleByData",articleService.groupByDate());
        model.addAllAttributes(map);
        return "template";
    }
    @GetMapping("/archives/{year}/{month}")
    public String archives(@PathVariable String year,@PathVariable String month, Model model){
        Map map = getMap();

        Map<String,String> html = new HashMap<>();
        html.put("file","index");
        html.put("name","index");
        map.put("html", html);
        model.addAttribute("articles",articleService.findByMonth(year+month));
        model.addAllAttributes(map);

        return "template";
    }
    /**
     * 友情链接
     * @param model
     * @return
     */
    @GetMapping("/friendLink")
    public String link(Model model){
        Map map = getMap();
        Map<String,String> html = new HashMap<>();
        html.put("file","label/link");
        html.put("name","link");
        map.put("html", html);
        model.addAttribute("links",linkService.findAll());
        model.addAllAttributes(map);
        return "template";
    }
    /**
     * 文章正文页
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/article/q/{id}")
    public String article(@PathVariable String id, Model model, HttpServletRequest request){
        Article article = articleService.findById(id);
        String ip = IPUtil.getIpAddr(request);
        //获取ip缓存
        Cache ipCache = cacheManager.getCache("ip");
        if (ipCache.get(id+ip)==null){//如果缓存为空
            //存入缓存
            ipCache.put(id+ip,ip);
            //浏览量加一
            article.setVisits(article.getVisits()+1);
            articleService.update(article);
        }
        model.addAttribute("article",article);
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
    @GetMapping("/{type}/q/{id}")
    public String channel(@PathVariable String type,@PathVariable String id, Model model){
        switch (type){
            case "channel": model.addAttribute("articles",channelService.findById(id).getArticle());
            break;
            case "column": model.addAttribute("articles",columnService.findById(id).getArticle());
            break;
            default: return "error";
        }
        model.addAttribute("pageInfo",pageInfoService.getInfo());
        Map<String,String> html = new HashMap<>();
        html.put("file","index");
        html.put("name","index");
        model.addAttribute("html",html);
        model.addAllAttributes(getMap());
        return "template";
    }
}
