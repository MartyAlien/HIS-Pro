package com.hrbu.his.bean.yi163;

/**
 * @ClassName: ChinaTotalList
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/17 11:45
 */
public class ChinaDay extends ChinaTotal {
    private String date;
    private String lastUpdateTime;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public Today getToday() {
        return super.getToday();
    }

    @Override
    public void setToday(Today today) {
        super.setToday(today);
    }

    @Override
    public Total getTotal() {
        return super.getTotal();
    }

    @Override
    public void setTotal(Total total) {
        super.setTotal(total);
    }

    @Override
    public ExtData getExtData() {
        return super.getExtData();
    }

    @Override
    public void setExtData(ExtData extData) {
        super.setExtData(extData);
    }
}
