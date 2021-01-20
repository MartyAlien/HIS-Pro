package com.hrbu.his.bean;

/**
 * @ClassName: UnpayPatient
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/06 11:19
 */
public class UnpayPatientVo {
    private Integer pid;
    private String pname;
    private String dname;
    private String deptname;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }
}
