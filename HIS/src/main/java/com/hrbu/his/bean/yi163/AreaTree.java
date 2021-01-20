package com.hrbu.his.bean.yi163;

import java.util.List;

/**
 * @ClassName: AreaTree
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/17 11:52
 */
public class AreaTree extends ChinaTotal {
    private String name; //地区名
    private String id; //地区编号
    private String lastUpdateTime; //最新截止日期
    private List<AreaTree> children; //下级城市

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public List<AreaTree> getChildren() {
        return children;
    }

    public void setChildren(List<AreaTree> children) {
        this.children = children;
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
