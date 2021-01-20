package com.hrbu.his.bean;

import java.util.List;

/**
 * @ClassName: SarsBean
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/16 13:52
 */
public class SarsBean {
    private List<LocationNum> data;
    private List<LocationNum> today;
    private List<LocationNum> g_today;
    private List<LocationNum> g_data;

    public List<LocationNum> getData() {
        return data;
    }

    public void setData(List<LocationNum> data) {
        this.data = data;
    }

    public List<LocationNum> getToday() {
        return today;
    }

    public void setToday(List<LocationNum> today) {
        this.today = today;
    }

    public List<LocationNum> getG_today() {
        return g_today;
    }

    public void setG_today(List<LocationNum> g_today) {
        this.g_today = g_today;
    }

    public List<LocationNum> getG_data() {
        return g_data;
    }

    public void setG_data(List<LocationNum> g_data) {
        this.g_data = g_data;
    }
}
