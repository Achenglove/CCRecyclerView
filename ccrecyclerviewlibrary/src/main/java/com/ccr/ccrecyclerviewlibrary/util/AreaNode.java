package com.ccr.ccrecyclerviewlibrary.util;

import java.util.List;

/**
 * 解析省市的JavaBean
 *
 * @author zhangyb@ifenguo.com
 * @createDate 2015年3月10日
 */
public class AreaNode {

    long id;
    String name;
    int level;
    List<AreaNode> children;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<AreaNode> getChildren() {
        return children;
    }

    public void setChildren(List<AreaNode> children) {
        this.children = children;
    }

}
