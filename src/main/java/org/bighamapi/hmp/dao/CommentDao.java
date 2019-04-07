package org.bighamapi.hmp.dao;

import org.bighamapi.hmp.pojo.Article;
import org.bighamapi.hmp.pojo.Column;
import org.bighamapi.hmp.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author bighamapi
 *
 */
public interface CommentDao extends JpaRepository<Comment,String>,JpaSpecificationExecutor<Comment>{
}
