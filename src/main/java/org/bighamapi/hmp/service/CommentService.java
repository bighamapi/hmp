package org.bighamapi.hmp.service;

import org.bighamapi.hmp.dao.ColumnDao;
import org.bighamapi.hmp.dao.CommentDao;
import org.bighamapi.hmp.pojo.Article;
import org.bighamapi.hmp.pojo.Column;
import org.bighamapi.hmp.pojo.Comment;
import org.bighamapi.hmp.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 服务层
 * 
 * @author bighamapi
 *
 */
@Service
@CacheConfig(cacheNames = "comment")
public class CommentService {

	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private IdWorker idWorker;
	@Autowired
	private CacheManager cacheManager;

	/**
	 * 查询全部列表
	 * @return
	 */
	@Cacheable(key = "0")
	public List<Comment> findAll() {
		return commentDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Comment> findSearch(Map whereMap, int page, int size) {
		Specification<Comment> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return commentDao.findAll(specification, pageRequest);
	}

	/**
	 * 评论总数
	 * @return
	 */
	public long count(){
		return commentDao.count();
	}
	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Comment> findSearch(Map whereMap) {
		Specification<Comment> specification = createSpecification(whereMap);
		return commentDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	@Cacheable(key = "#id")
	public Comment findById(String id) {
		return commentDao.findById(id).get();
	}

	@Cacheable(key = "#aId")
	public List<Comment> findByArticleId(String aId){
		return commentDao.findByArticleId(aId);
	}
	/**
	 * 增加
	 * @param comment
	 */
	public void add(Comment comment) {
		if (comment.getEmail()==null){
			throw new RuntimeException("没有权限！");
		}
		comment.setId( idWorker.nextId()+"");
		comment.setCreateTime(new Date());

		commentDao.save(comment);
		//将all缓存清除
		Cache cache = cacheManager.getCache("article");
		if (cache.get("0")!=null){
			cache.evict("0");
			cache.evict(comment.getArticle().getId());
		}
	}

	/**
	 * 修改
	 * @param comment
	 */
	public void update(Comment comment) {
		commentDao.save(comment);
		//将all缓存清除
		Cache cache = cacheManager.getCache("article");
		if (cache.get("0")!=null){
			cache.evict("0");
			cache.evict(comment.getId());
		}
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		commentDao.deleteById(id);
		//将all缓存清除
		Cache cache = cacheManager.getCache("article");
		if (cache.get("0")!=null){
			cache.evict("0");
			cache.evict(id);
		}
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Comment> createSpecification(Map searchMap) {

		return new Specification<Comment>() {

			@Override
			public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                if (searchMap!= null) {
					if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
						predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
					}
					//
					if (searchMap.get("nickname") != null && !"".equals(searchMap.get("nickname"))) {
						predicateList.add(cb.like(root.get("nickname").as(String.class), "%" + (String) searchMap.get("nickname") + "%"));
					}
					//
					if (searchMap.get("content") != null && !"".equals(searchMap.get("content"))) {
						predicateList.add(cb.like(root.get("content").as(String.class), "%" + (String) searchMap.get("content") + "%"));
					}
					//
					if (searchMap.get("email") != null && !"".equals(searchMap.get("email"))) {
						predicateList.add(cb.like(root.get("email").as(String.class), "%" + (String) searchMap.get("email") + "%"));
					}
					if (searchMap.get("parentId") != null && !"".equals(searchMap.get("parentId"))) {
						predicateList.add(cb.like(root.get("parentId").as(String.class), "%" + (String) searchMap.get("parentId") + "%"));
					}
				}
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
