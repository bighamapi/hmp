package org.bighamapi.hmp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 实体类
 * @author bighamapi
 *
 */
@Entity
@Table(name="bjzt_article")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Article implements Serializable{

	@Id
	private String id;//ID

	@ManyToOne(fetch=FetchType.EAGER)
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"article"})
	private Column column;//专栏

	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH},fetch=FetchType.EAGER)
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"article"})
	private List<Channel> channel;//所属频道

	private String username;//用户名
	private String title;//标题

	private String summary;//摘要

	@javax.persistence.Column(columnDefinition = "text")
	private String content;//文章正文

	private java.util.Date createTime;//发表日期
	private java.util.Date updateTime;//修改日期
	private String isPublic;//是否公开
	private String isTop;//是否置顶
	private Integer visits;//浏览量
	private Integer comments;//评论数
	private String url;//URL

	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH},mappedBy = "article")
	@JsonIgnoreProperties(ignoreUnknown = true, value = {"article"})
	private List<Comment> comment;//评论

	
	public String getId() {		
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
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

	public List<Channel> getChannel() {
		return channel;
	}

	public void setChannel(List<Channel> channel) {
		this.channel = channel;
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


	public Integer getVisits() {
		return visits;
	}
	public void setVisits(Integer visits) {
		this.visits = visits;
	}


	public Integer getComments() {
		return comments;
	}
	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public String getUrl() {		
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Article{" +
				"id='" + id + '\'' +
				", column=" + column +
				", channel=" + channel +
				", username='" + username + '\'' +
				", title='" + title + '\'' +
				", summary='" + summary + '\'' +
				", content='" + content + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", isPublic='" + isPublic + '\'' +
				", isTop='" + isTop + '\'' +
				", visits=" + visits +
				", comments=" + comments +
				", url='" + url + '\'' +
//				", comment=" + comment +
				'}';
	}
}
