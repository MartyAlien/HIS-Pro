package com.hrbu.his.bean;

/**
 * @ClassName: Level
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/21 11:47
 */
public class Level {
    private Integer id;
    private String levelname;
    private Integer cost;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
