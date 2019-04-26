package org.bjzt.bjzt;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.bighamapi.hmp.HmpApplication;
import org.bighamapi.hmp.service.ArticleService;
import org.bighamapi.hmp.util.LuceneSearchUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = HmpApplication.class)
public class LuceneDemo {
    @Autowired
    private ArticleService articleService;
    @Test
    public void demo() throws Exception {
        // 1. 准备中文分词器
        IKAnalyzer analyzer = new IKAnalyzer();
        // 2. 索引
        Directory index = LuceneSearchUtil.createIndex(analyzer,articleService.findAll());
        // 3. 查询器
        String keyword = "java";
        Query query = new MultiFieldQueryParser(new String[]{"title","content","channel"}, analyzer).parse(keyword);
        // 4. 搜索
        IndexReader reader = DirectoryReader.open(index);

        IndexSearcher searcher = new IndexSearcher(reader);

        //第一个参数是查询对象，第二个参数是查询结果返回的最大值
        ScoreDoc[] hits = searcher.search(query, 10).scoreDocs;

        // 5. 显示查询结果
        LuceneSearchUtil.SearchResultsId(searcher, hits);
        // 6. 关闭查询
        reader.close();
    }
    @Test
    public void IKDemo() throws IOException {
        IKAnalyzer analyzer = new IKAnalyzer();
        TokenStream ts= analyzer.tokenStream("name", "测");
        ts.reset();
        while(ts.incrementToken()){
            System.out.println(ts.reflectAsString(false));
        }
    }
}