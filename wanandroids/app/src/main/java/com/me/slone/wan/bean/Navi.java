package com.me.slone.wan.bean;

import java.util.List;

/**
 * Author：diankun
 * Time：20-7-21 上午10:33
 * Description: 导航数据
 */
public class Navi {

    private List<Article> articles;
    private int cid;
    private String name;

    public List<Article> getArticles() {
        return articles;
    }

    public int getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }


    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setName(String name) {
        this.name = name;
    }
}
