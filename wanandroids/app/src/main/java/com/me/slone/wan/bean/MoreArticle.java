package com.me.slone.wan.bean;

import java.util.List;

/**
 * Author：diankun
 * Time：20-7-17 下午4:52
 * Description:
 */
public class MoreArticle {

    private int curPage;
    private List<Article> datas;

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setDatas(List<Article> datas) {
        this.datas = datas;
    }

    public int getCurPage() {
        return curPage;
    }

    public List<Article> getDatas() {
        return datas;
    }
}
