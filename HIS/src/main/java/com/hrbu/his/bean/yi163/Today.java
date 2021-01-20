package com.hrbu.his.bean.yi163;

/**
 * @ClassName: Today
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/17 11:35
 */
public class Today extends BaseData {
    private Integer storeConfirm; //当日新增现存确诊

    public Integer getStoreConfirm() {
        return storeConfirm;
    }

    public void setStoreConfirm(Integer storeConfirm) {
        this.storeConfirm = storeConfirm;
    }

    @Override
    public Integer getConfirm() {
        return super.getConfirm();
    }

    @Override
    public void setConfirm(Integer confirm) {
        super.setConfirm(confirm);
    }

    @Override
    public Integer getSuspect() {
        return super.getSuspect();
    }

    @Override
    public void setSuspect(Integer suspect) {
        super.setSuspect(suspect);
    }

    @Override
    public Integer getHeal() {
        return super.getHeal();
    }

    @Override
    public void setHeal(Integer heal) {
        super.setHeal(heal);
    }

    @Override
    public Integer getDead() {
        return super.getDead();
    }

    @Override
    public void setDead(Integer dead) {
        super.setDead(dead);
    }

    @Override
    public Integer getSevere() {
        return super.getSevere();
    }

    @Override
    public void setSevere(Integer severe) {
        super.setSevere(severe);
    }

    @Override
    public Integer getInput() {
        return super.getInput();
    }

    @Override
    public void setInput(Integer input) {
        super.setInput(input);
    }
}
