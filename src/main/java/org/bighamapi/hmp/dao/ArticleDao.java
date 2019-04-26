package org.bighamapi.hmp.dao;

import org.bighamapi.hmp.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * 数据访问接口
 * @author bighamapi
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    @Modifying
    @Query(value = "select * from bjzt_article  order by visits DESC limit 0,?",nativeQuery = true)
    List<Article> findByVisits(int num);

    @Modifying
    @Query(value = "select CONCAT(YEAR(create_time),'/',DATE_FORMAT(create_time,'%m')) months, count(id) as total" +
            " from bjzt_article " +
            "WHERE `create_time` BETWEEN (SELECT min(create_time) from hmp_article) AND now() group by months;",
            nativeQuery = true)
    List<Map<String, String>> groupByDate();

    /**
     * 这里的格式要为 年月
     * ps: 201904
     * @param month
     * @return
     */
    @Modifying
    @Query(value = "select * from bjzt_article where DATE_FORMAT( create_time, '%Y%m' ) = ? ORDER BY create_time desc",nativeQuery = true)
    List<Article> findByMonth(String month);

//    @Query(value = "select sum(?) from hmp_article",nativeQuery = true)
//    Long sumByAttribute(String attribute);
}
