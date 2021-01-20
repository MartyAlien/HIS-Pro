package com.hrbu.his.bean.yi163;

/**
 * @ClassName: BaseData
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/17 11:18
 */
public class BaseData {
    private Integer confirm; // 确诊人数
    private Integer suspect; // 疑似病例人数
    private Integer heal; // 治愈人数
    private Integer dead; // 死亡人数
    private Integer severe; // 严重程度
    private Integer input; // 境外输入

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public Integer getSuspect() {
        return suspect;
    }

    public void setSuspect(Integer suspect) {
        this.suspect = suspect;
    }

    public Integer getHeal() {
        return heal;
    }

    public void setHeal(Integer heal) {
        this.heal = heal;
    }

    public Integer getDead() {
        return dead;
    }

    public void setDead(Integer dead) {
        this.dead = dead;
    }

    public Integer getSevere() {
        return severe;
    }

    public void setSevere(Integer severe) {
        this.severe = severe;
    }

    public Integer getInput() {
        return input;
    }

    public void setInput(Integer input) {
        this.input = input;
    }
}
