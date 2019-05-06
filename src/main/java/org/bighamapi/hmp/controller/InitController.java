package org.bighamapi.hmp.controller;

import org.bighamapi.hmp.dto.PageInfo;
import org.bighamapi.hmp.entity.Result;
import org.bighamapi.hmp.entity.StatusCode;
import org.bighamapi.hmp.pojo.Article;
import org.bighamapi.hmp.pojo.Channel;
import org.bighamapi.hmp.pojo.User;
import org.bighamapi.hmp.service.ArticleService;
import org.bighamapi.hmp.service.PageInfoService;
import org.bighamapi.hmp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class InitController {

    @Autowired
    UserService userService;
    @Autowired
    PageInfoService pageInfoService;
    @Autowired
    ArticleService articleService;
    /**
     * 初始化
     */
    @GetMapping("/init")
    public String init(){
        return "init/init";
    }

    @PostMapping("/init")
    @ResponseBody
    public Result init2(@RequestBody Map<String,Object> initData) {
        Map<String,String> userMap = (Map<String, String>) initData.get("user");
        Map<String,String> info = (Map<String, String>) initData.get("pageInfo");

        User user = new User();
        user.setUsername(userMap.get("username"));
        user.setPassword(userMap.get("password"));

        PageInfo pageInfo = new PageInfo();
        pageInfo.setTitle(info.get("title"));
        if (info.get("toTitle")!=null){
            pageInfo.setToTitle(info.get("toTitle"));
        }
        if (info.get("footer")!=null){
            pageInfo.setFooter(info.get("footer"));
        }

        if (info.get("notice")!=null){
            pageInfo.setNotice(info.get("notice"));
        }
        System.out.println(info);
        //初始化博客配置
        userService.newUser(user);
        pageInfoService.init(pageInfo);
        //生成第一篇文章
        Article article = new Article();
        article.setTitle("hello world");
        ArrayList<Channel> channels = new ArrayList<>();
        Channel channel = new Channel();
        channel.setName("hello");
        article.setChannel(channels);
        article.setContent("hello world");
        article.setSummary("hello world");
        article.setIsPublic("true");
        article.setUsername(user.getUsername());
        articleService.add(article);

        return new Result(true, StatusCode.OK,"初始化成功");
    }
}
