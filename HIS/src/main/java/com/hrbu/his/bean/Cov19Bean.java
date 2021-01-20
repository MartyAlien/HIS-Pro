package com.hrbu.his.bean;

import com.hrbu.his.bean.yi163.ChinaDay;
import com.hrbu.his.bean.yi163.ChinaTotal;

import java.util.List;

/**
 * @ClassName: Cov19Bean
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/17 13:11
 */
public class Cov19Bean extends SarsBean {
    private Integer code;
    private ChinaTotal chinaTotal; //当日国内总新增
    private List<ChinaDay> chinaDayList; //国内历史数据

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

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

    @Override
    public List<LocationNum> getData() {
        return super.getData();
    }

    @Override
    public void setData(List<LocationNum> data) {
        super.setData(data);
    }

    @Override
    public List<LocationNum> getToday() {
        return super.getToday();
    }

    @Override
    public void setToday(List<LocationNum> today) {
        super.setToday(today);
    }

    @Override
    public List<LocationNum> getG_today() {
        return super.getG_today();
    }

    @Override
    public void setG_today(List<LocationNum> g_today) {
        super.setG_today(g_today);
    }

    @Override
    public List<LocationNum> getG_data() {
        return super.getG_data();
    }

    @Override
    public void setG_data(List<LocationNum> g_data) {
        super.setG_data(g_data);
    }
}
