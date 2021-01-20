package com.hrbu.his.bean;

import java.util.List;

/**
 * @ClassName: LocationNum
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/16 13:53
 */
public class LocationNum {
    private String id;
    private String name;
    private Integer value;
    private String lastUpdateTime;
    private List<LocationNum> children;

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<LocationNum> getChildren() {
        return children;
    }

    public void setChildren(List<LocationNum> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
