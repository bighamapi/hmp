package org.bighamapi.hmp.dao;

import org.bighamapi.hmp.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author bighamapi
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    @Modifying
    @Query(value = "update tb_article set state=1 where id = ?",nativeQuery = true)
    void updateState(String id);

}
