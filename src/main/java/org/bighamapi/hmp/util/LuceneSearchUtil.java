package org.bighamapi.hmp.util;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.bighamapi.hmp.pojo.Article;
import org.bighamapi.hmp.pojo.Channel;
import org.bighamapi.hmp.service.ArticleService;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class LuceneSearchUtil{
    /**
     * 查询结果展示
     * @param searcher
     * @param hits
     * @throws Exception
     */
    public static List<String> SearchResultsId(IndexSearcher searcher, ScoreDoc[] hits) throws Exception {
//        System.out.println("找到 " + hits.length + " 个命中.");
//        System.out.println("序号\t匹配度得分\t结果");
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < hits.length; ++i) {
            ScoreDoc scoreDoc= hits[i];
            int docId = scoreDoc.doc;
            Document d = searcher.doc(docId);
            List<IndexableField> fields = d.getFields();//返回结果集类似ResultSet
//            System.out.print((i + 1) );
//            System.out.print("\t" + scoreDoc.score);//匹配度得分
            for (IndexableField f : fields) {
                if("id".equals(f.name())){
//                    System.out.print("id: "+f.stringValue());
                    ids.add(f.stringValue());
                    break;
                }
            }
        }
        return ids;
    }

    /**
     * 创建索引
     * @param analyzer ik
     * @param articles 文章列表（为这个数据建立索引）
     * @return 索引
     * @throws IOException
     */
    public static Directory createIndex(IKAnalyzer analyzer,List<Article> articles) throws IOException {

        Directory index = new RAMDirectory();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        IndexWriter writer = new IndexWriter(index, config);
        List<Article> products = articles;

        for (Article p : products) {
            addDoc(writer, p);
        }
        writer.close();
        return index;
    }

    /**
     * 将Article 转为 Document 对象
     * @param w 索引写入对象
     * @param p 文章对象
     * @throws IOException 写入错误
     */
    private static void addDoc(IndexWriter w, Article p) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("id", String.valueOf(p.getId()), Field.Store.YES));
        doc.add(new TextField("title", p.getTitle(), Field.Store.YES));
        doc.add(new TextField("content", p.getContent(), Field.Store.YES));
        List<Channel> channel = p.getChannel();
        StringBuffer channelStr = new StringBuffer();
        for (Channel c :channel ) {
            channelStr.append(c+"\t");
        }
        doc.add(new TextField("channel", channelStr.toString(), Field.Store.YES));
        w.addDocument(doc);
    }

    /**
     * 根据id删除索引
     * @param analyzer ik对象
     * @param index 索引
     * @param id id
     * @throws IOException io异常
     */
    public void deleteById(IKAnalyzer analyzer, Directory index,String id) throws IOException {
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(index, config);
        indexWriter.deleteDocuments(new Term("id", id));
        indexWriter.commit();
        indexWriter.close();
    }
}
