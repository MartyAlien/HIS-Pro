package com.hrbu.his.bean;

import java.time.LocalDateTime;

/**
 * @ClassName: Medicalrecord
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/28 14:30
 */
public class Medicalrecord {
    private Integer id;
    private Integer pid;
    private String description;
    private String medicalhistory;
    private String familyhistory;
    private String initialresult;
    private String result;
    private String finalresult;
    private Integer status;
    private Integer operator;
    private LocalDateTime operatedate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedicalhistory() {
        return medicalhistory;
    }

    public void setMedicalhistory(String medicalhistory) {
        this.medicalhistory = medicalhistory;
    }

    public String getFamilyhistory() {
        return familyhistory;
    }

    public void setFamilyhistory(String familyhistory) {
        this.familyhistory = familyhistory;
    }

    public String getInitialresult() {
        return initialresult;
    }

    public void setInitialresult(String initialresult) {
        this.initialresult = initialresult;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFinalresult() {
        return finalresult;
    }

    public void setFinalresult(String finalresult) {
        this.finalresult = finalresult;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public LocalDateTime getOperatedate() {
        return operatedate;
    }

    public void setOperatedate(LocalDateTime operatedate) {
        this.operatedate = operatedate;
    }
}