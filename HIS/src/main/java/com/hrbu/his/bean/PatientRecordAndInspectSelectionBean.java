package com.hrbu.his.bean;

import java.util.List;

/**
 * @ClassName: PatientRecordAndInspectSelectionBean
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/07 11:09
 */
public class PatientRecordAndInspectSelectionBean {
    private PatientBean patientBean;

    private List<InspectitemBean> selectedInspectList ;

    public PatientBean getPatientBean() {
        return patientBean;
    }

    public void setPatientBean(PatientBean patientBean) {
        this.patientBean = patientBean;
    }

    public List<InspectitemBean> getSelectedInspectList() {
        return selectedInspectList;
    }

    public void setSelectedInspectList(List<InspectitemBean> selectedInspectList) {
        this.selectedInspectList = selectedInspectList;
    }
}
