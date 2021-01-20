package com.hrbu.his.bean.yi163;

import java.util.List;

/**
 * @ClassName: Data
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/17 11:31
 */
public class Data {
    private ChinaTotal chinaTotal; //当日国内总新增
    private List<ChinaDay> chinaDayList; //国内历史数据
    private String lastUpdateTime; //国内数据更新日期
    private String overseaLastUpdateTime; //海外数据更新日期
    private List<AreaTree> areaTree; //数据树
    public ChinaTotal getChinaTotal() {
        return chinaTotal;
    }

    public void setChinaTotal(ChinaTotal chinaTotal) {
        this.chinaTotal = chinaTotal;
    }

    public List<ChinaDay> getChinaDayList() {
        return chinaDayList;
    }

    public void setChinaDayList(List<ChinaDay> chinaDayList) {
        this.chinaDayList = chinaDayList;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getOverseaLastUpdateTime() {
        return overseaLastUpdateTime;
    }

    public void setOverseaLastUpdateTime(String overseaLastUpdateTime) {
        this.overseaLastUpdateTime = overseaLastUpdateTime;
    }

    public List<AreaTree> getAreaTree() {
        return areaTree;
    }

    public void setAreaTree(List<AreaTree> areaTree) {
        this.areaTree = areaTree;
    }
}
