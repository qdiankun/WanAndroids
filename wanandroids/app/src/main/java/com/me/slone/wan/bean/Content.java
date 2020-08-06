package com.me.slone.wan.bean;

import java.util.List;

/**
 * Author：diankun
 * Time：20-8-6 下午6:58
 * Description:
 */
public class Content {

    private int curPage;
    private List<Article> datas;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;

    public Content(int curPage, List<Article> datas, int offset, boolean over, int pageCount, int size, int total) {
        this.curPage = curPage;
        this.datas = datas;
        this.offset = offset;
        this.over = over;
        this.pageCount = pageCount;
        this.size = size;
        this.total = total;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setDatas(List<Article> datas) {
        this.datas = datas;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurPage() {
        return curPage;
    }

    public List<Article> getDatas() {
        return datas;
    }

    public int getOffset() {
        return offset;
    }

    public boolean isOver() {
        return over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getSize() {
        return size;
    }

    public int getTotal() {
        return total;
    }
}
