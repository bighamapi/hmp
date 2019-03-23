package org.bighamapi.hmp.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体类
 * @author bighamapi
 *
 */
@Entity
@Table(name="hmp_article")
public class Article implements Serializable{

	@Id
	private String id;//ID

	private String columnId;//专栏ID
	private String userId;//用户ID
	private String title;//标题
	private String content;//文章正文
	private java.util.Date createTime;//发表日期
	private java.util.Date updateTime;//修改日期
	private String isPublic;//是否公开
	private String isTop;//是否置顶
	private Integer visits;//浏览量
	private Integer comment;//评论数
	private String channelId;//所属频道
	private String url;//URL
	private String type;//类型

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {		
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Integer getVisits() {
		return visits;
	}
	public void setVisits(Integer visits) {
		this.visits = visits;
	}


	public Integer getComment() {		
		return comment;
	}
	public void setComment(Integer comment) {
		this.comment = comment;
	}


	public String getChannelId() {
		return channelId;
	}
	public void setChannelid(String channelId) {
		this.channelId = channelId;
	}

	public String getUrl() {		
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {		
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	
}