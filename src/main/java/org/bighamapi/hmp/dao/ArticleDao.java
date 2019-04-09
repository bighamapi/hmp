package org.bighamapi.hmp.dao;

import org.bighamapi.hmp.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 * @author bighamapi
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    @Modifying
    @Query(value = "select * from hmp_article  order by visits DESC limit 0,?",nativeQuery = true)
    List<Article> findByVisits(int num);
}
