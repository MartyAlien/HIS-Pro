package com.hrbu.his.bean;

/**
 * @ClassName: Doctor
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/20 14:07
 */
public class Doctor {
    private Integer id;
    private String dname;
    private String deptid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }
}
