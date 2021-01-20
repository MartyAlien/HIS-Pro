package com.hrbu.his.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @ClassName: Patient
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/20 12:48
 */
public class Patient extends Patients{
    private Integer pid;
    private String pname;
    private String sex;
    private Integer age;
    private String birthday;
    private String idcard;
    private String address;
    private Integer levelid;
    private Integer deptid;
    private Integer doctorid;
    private LocalDate createdate;
    private Integer cost;
    private String pstatus;
    private Integer status;
    private Integer operator;
    private LocalDateTime operatedate;
    private Integer delivery;
    private Integer money;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getLevelid() {
        return levelid;
    }

    public void setLevelid(Integer levelid) {
        this.levelid = levelid;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public Integer getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(Integer doctorid) {
        this.doctorid = doctorid;
    }



    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getPstatus() {
        return pstatus;
    }

    public void setPstatus(String pstatus) {
        this.pstatus = pstatus;
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

    public LocalDate getCreatedate() {
        return createdate;
    }
    public String getCreatedatebystr() {
        if(this.createdate==null){
            return "";
        }
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = this.createdate.format(pattern);
        return format;
    }

    public void setCreatedate(Object createdate) {
        if(createdate instanceof String){
            // 接收浏览器传回来的字符串时间格式
            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse((String)createdate, pattern);
            this.createdate = date;
        }else if(createdate instanceof LocalDate){
            this.createdate=(LocalDate) createdate;
        }

    }

    public void setCreatedatebystr(String createdate) {
        if(createdate.equals("")||createdate==null){
            return;
        }
        // 接收浏览器传回来的字符串时间格式
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(createdate, pattern);
        this.createdate = date;
    }

    public LocalDateTime getOperatedate() {
        return operatedate;
    }

    public void setOperatedate(Object operatedate) {
        if(operatedate instanceof LocalDateTime){
            this.operatedate =(LocalDateTime) operatedate;
        }else {
            this.operatedate=null;
        }
    }

    public Integer getDelivery() {
        return delivery;
    }

    public void setDelivery(Integer delivery) {
        this.delivery = delivery;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

}
