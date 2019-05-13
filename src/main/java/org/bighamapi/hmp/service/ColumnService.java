package org.bighamapi.hmp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

import org.bighamapi.hmp.dao.ColumnDao;
import org.bighamapi.hmp.pojo.Column;

/**
 * 服务层
 * 
 * @author bighamapi
 *
 */
@Service
@CacheConfig(cacheNames = "column")
public class ColumnService {

	@Autowired
	private ColumnDao columnDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Column> findAll() {
		return columnDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Column> findSearch(Map whereMap, int page, int size) {
		Specification<Column> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return columnDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Column> findSearch(Map whereMap) {
		Specification<Column> specification = createSpecification(whereMap);
		return columnDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	@Cacheable(value = "column",key="#id")
	public Column findById(String id) {
		return columnDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param column
	 */
	public void add(Column column) {
		column.setId( idWorker.nextId()+"" );
		column.setCreateTime(new Date());
		column.setUpdateTime(new Date());
		columnDao.save(column);
	}

	/**
	 * 修改
	 * @param column
	 */
	@CacheEvict(value = "column",key="#column.id")
	public void update(Column column) {
		column.setUpdateTime(new Date());
		columnDao.save(column);

	}

	/**
	 * 删除
	 * @param id
	 */
	@CacheEvict(key = "#id")
	public void deleteById(String id) {
		columnDao.deleteById(id);
		//将all缓存清除
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Column> createSpecification(Map searchMap) {

		return new Specification<Column>() {

			@Override
			public Predicate toPredicate(Root<Column> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
				if (searchMap!= null) {
					if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
						predicateList.add(cb.equal(root.get("id").as(String.class), searchMap.get("id")));
					}
					//
					if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
						predicateList.add(cb.equal(root.get("name").as(String.class), searchMap.get("name")));
					}
					//
					if (searchMap.get("summary") != null && !"".equals(searchMap.get("summary"))) {
						predicateList.add(cb.like(root.get("summary").as(String.class), "%" + (String) searchMap.get("summary") + "%"));
					}
				}
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
