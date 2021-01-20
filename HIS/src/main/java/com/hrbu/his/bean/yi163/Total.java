package com.hrbu.his.bean.yi163;

/**
 * @ClassName: Total
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/17 11:37
 */
public class Total extends BaseData {
    private Integer storeConfirm;
    private Integer newConfirm;
    private Integer newDead;
    private Integer newHeal;

    public Integer getStoreConfirm() {
        return storeConfirm;
    }

    public void setStoreConfirm(Integer storeConfirm) {
        this.storeConfirm = storeConfirm;
    }

    public Integer getNewConfirm() {
        return newConfirm;
    }

    public void setNewConfirm(Integer newConfirm) {
        this.newConfirm = newConfirm;
    }

    public Integer getNewDead() {
        return newDead;
    }

    public void setNewDead(Integer newDead) {
        this.newDead = newDead;
    }

    public Integer getNewHeal() {
        return newHeal;
    }

    public void setNewHeal(Integer newHeal) {
        this.newHeal = newHeal;
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
