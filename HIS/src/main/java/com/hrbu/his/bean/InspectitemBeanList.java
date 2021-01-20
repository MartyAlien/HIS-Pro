package com.hrbu.his.bean;

import java.util.List;

/**
 * @ClassName: InspectitemBeanList
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/05 19:55
 */
public class InspectitemBeanList {
    private List<InspectitemBean> selectedInspectList;

    public List<InspectitemBean> getSelectedInspectList() {
        return selectedInspectList;
    }

    public void setSelectedInspectList(List<InspectitemBean> selectedInspectList) {
        this.selectedInspectList = selectedInspectList;
    }
}
