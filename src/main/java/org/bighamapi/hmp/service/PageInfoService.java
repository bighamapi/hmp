package org.bighamapi.hmp.service;

import org.bighamapi.hmp.dao.PageInfoDao;
import org.bighamapi.hmp.dto.PageInfo;
import org.bighamapi.hmp.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * 服务层
 * 
 * @author bighamapi
 *
 */
@Service
@Transactional
public class PageInfoService {

	@Autowired
	private PageInfoDao pageInfoDao;

	/**
	 * 查询页面信息
	 * @return
	 */
	@Cacheable(value = "pageInfo",key = "0")
	public PageInfo getInfo() {
		if (pageInfoDao.findAll().isEmpty()){
			PageInfo pageInfo = new PageInfo();
			pageInfoDao.save(pageInfo);
			return pageInfo;
		}
		return pageInfoDao.findAll().get(0);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public PageInfo findById(int id) {
		//redis
		PageInfo pageInfo = null;//(Article) redisTemplate.opsForValue().get("article_"+id);
		if(pageInfo == null){
			pageInfo = pageInfoDao.findById(id).get();
			//redisTemplate.opsForValue().set("article_"+id, article);
		}
		return pageInfo;
	}

	/**
	 * 修改
	 * @param pageInfo
	 */
	@CacheEvict(value = "pageInfo",key = "0")
	public void update(PageInfo pageInfo) {
		if (pageInfo.getId()==null){
			pageInfo.setId(getInfo().getId());
		}
		System.out.println(pageInfo);
		pageInfoDao.save(pageInfo);
	}

}
