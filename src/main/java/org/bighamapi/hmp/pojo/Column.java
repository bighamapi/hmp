package org.bighamapi.hmp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="bjzt_column")
@JsonIgnoreProperties(ignoreUnknown = true, value =
        {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Column implements Serializable{

    @Id
    private String id;//ID

    @OneToMany(cascade = CascadeType.DETACH , mappedBy = "column",fetch=FetchType.EAGER)
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"column","comment","channel"})
    private List<Article> article;

	private String name;//专栏名称
	private String summary;//专栏简介
	private java.util.Date createTime;//创建日期
	private java.util.Date updateTime;//更新日期
	private String state;//状态

	public List<Article> getArticle() {
		return article;
	}

	public void setArticle(List<Article> article) {
		this.article = article;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {		
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {		
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
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

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}


	
}
