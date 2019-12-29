package org.bighamapi.hmp.controller;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.bighamapi.hmp.pojo.Article;
import org.bighamapi.hmp.service.*;
import org.bighamapi.hmp.util.LuceneSearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class LuceneController {
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
    /**
     * 查询
     * @param model
     * @return
     */
    @GetMapping("/search/{keyword}")
    public String index(@PathVariable String keyword, Model model) throws Exception {
        // 1. 准备中文分词器
        IKAnalyzer analyzer = new IKAnalyzer();
        // 2. 索引
        Directory index = LuceneSearchUtil.createIndex(analyzer,articleService.findAll());
        Query query = new MultiFieldQueryParser(new String[]{"title","content","channel"}, analyzer).parse(keyword);
        // 4. 搜索
        IndexReader reader = DirectoryReader.open(index);

        IndexSearcher searcher = new IndexSearcher(reader);

        //第一个参数是查询对象，第二个参数是查询结果返回的最大值
        ScoreDoc[] hits = searcher.search(query, 10).scoreDocs;

        // 5. 显示查询结果
        List<String> ids = LuceneSearchUtil.SearchResultsId(searcher, hits);
        //根据ids找到文章list
        List<Article> articles = ids.stream().map(id -> articleService.findById(id)).collect(Collectors.toList());

        // 6. 关闭查询
        reader.close();
        //themaleaf所需要的值
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
        //查询结果
        model.addAttribute("articles",articles);

        Map<String,String> html = new HashMap<>();
        html.put("file","index");
        html.put("name","index");
        map.put("html", html);
        model.addAllAttributes(map);
        return "template";
    }
}
