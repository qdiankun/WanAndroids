package com.me.slone.wan.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author：diankun
 * Time：20-7-23 下午4:54
 * Description: 体系数据
 * <p>
 * "courseId": 13,
 * "id": 169,
 * "name": "gradle",
 * "order": 1001,
 * "parentChapterId": 150,
 * "userControlSetTop": false,
 * "visible": 1
 */
public class Tree implements Serializable {

    private List<Children> children;
    private int courseId;
    private int id;
    private String name;
    private int order;
    private int parentChapterId;
    private boolean userControlSetTop;
    private int visible;

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public void setUserControlSetTop(boolean userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public List<Children> getChildren() {
        return children;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public boolean isUserControlSetTop() {
        return userControlSetTop;
    }

    public int getVisible() {
        return visible;
    }
}
