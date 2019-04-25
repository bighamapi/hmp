package org.bighamapi.hmp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="hmp_pageInfo")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class PageInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;//主标题
    private String toTitle;//子标题
    private String meta;//网站关键字
    private String head;//自己实现的头部html
    private String notice;//公告
    private String footer;//网页底部信息

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getToTitle() {
        return toTitle;
    }

    public void setToTitle(String toTitle) {
        this.toTitle = toTitle;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", toTitle='" + toTitle + '\'' +
                ", meta='" + meta + '\'' +
                ", head='" + head + '\'' +
                ", notice='" + notice + '\'' +
                ", footer='" + footer + '\'' +
                '}';
    }
}
