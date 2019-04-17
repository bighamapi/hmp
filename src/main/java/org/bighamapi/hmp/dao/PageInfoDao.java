package org.bighamapi.hmp.dao;

import org.bighamapi.hmp.dto.PageInfo;
import org.bighamapi.hmp.pojo.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 数据访问接口
 * @author bighamapi
 *
 */
public interface PageInfoDao extends JpaRepository<PageInfo,Integer>,JpaSpecificationExecutor<PageInfo>{
	
}
