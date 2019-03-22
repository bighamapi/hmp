package org.bighamapi.hmp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.bighamapi.hmp.pojo.Channel;
/**
 * 数据访问接口
 * @author bighamapi
 *
 */
public interface ChannelDao extends JpaRepository<Channel,String>,JpaSpecificationExecutor<Channel>{
	
}
