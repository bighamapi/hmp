package org.bighamapi.hmp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bighamapi.hmp.service.ColumnService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "hmp_comment")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Comment implements Serializable {

    @Id
    private String id;

    private String content;
    private Date createTime;
    private String email;
    private String nickname;
    @ManyToOne
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"comment"})
    private Article article;

    private String parentId;//上级ID

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", article=" + article +
                ", parentId='" + parentId + '\'' +
                '}';
    }
}
