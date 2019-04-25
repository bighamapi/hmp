package org.bighamapi.hmp.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name="hmp_channel")
@JsonIgnoreProperties(ignoreUnknown = true, value =
{"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Channel implements Serializable {

    @Id
    private String id;//ID

    @ManyToMany(cascade = CascadeType.DETACH,fetch=FetchType.EAGER,mappedBy = "channel")
    @JsonIgnoreProperties(ignoreUnknown = true, value = {"channel"})
    private List<Article> article;
    private String name;//频道名称
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

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}