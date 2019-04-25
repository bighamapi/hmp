package org.bighamapi.hmp.controller;

import org.bighamapi.hmp.dto.PageInfo;
import org.bighamapi.hmp.dto.RSSFeedViewer;
import org.bighamapi.hmp.dto.RSSMessage;
import org.bighamapi.hmp.pojo.Article;
import org.bighamapi.hmp.service.ArticleService;
import org.bighamapi.hmp.service.PageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class RSSController {

    @Autowired
    private RSSFeedViewer RSSFeedViewer;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value="/rss", method = RequestMethod.GET)
    public ModelAndView getFeedInRss(ModelAndView mav, HttpServletRequest request) {

        String contextPath = request.getRequestURL().substring(0,request.getRequestURL().indexOf(request.getRequestURI()));

        List<RSSMessage> items = new ArrayList<>();

        List<Article> articles = articleService.findAll();
        for (Article article :articles) {
            RSSMessage articleItem = new RSSMessage();
            articleItem.setTitle(article.getTitle());
            articleItem.setUrl(contextPath+article.getUrl());
            articleItem.setSummary(article.getSummary());
            articleItem.setCreatedDate(new Date());
            items.add(articleItem);
        }
        mav.setView(RSSFeedViewer);
        mav.addObject("feedContent", items);
        return mav;
    }
}
