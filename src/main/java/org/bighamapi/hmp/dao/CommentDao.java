package org.bighamapi.hmp.dao;

import org.bighamapi.hmp.pojo.Article;
import org.bighamapi.hmp.pojo.Column;
import org.bighamapi.hmp.pojo.Comment;
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
public interface CommentDao extends JpaRepository<Comment,String>,JpaSpecificationExecutor<Comment>{

    @Modifying
    @Query(value = "select * from bjzt_comment where article_id = ? order by create_time desc",nativeQuery = true)
    List<Comment> findByArticleId(String aId);
}
