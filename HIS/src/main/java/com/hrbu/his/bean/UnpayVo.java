package com.hrbu.his.bean;

/**
 * @ClassName: UnpayVo
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/06 10:54
 */
public class UnpayVo {
    private Integer pid;
    private String pname;
    private Integer inspectid;
    private String inspectname;
    private Double price;
    private Integer amount;
    private Double totalPay;

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

    public Integer getInspectid() {
        return inspectid;
    }

    public void setInspectid(Integer inspectid) {
        this.inspectid = inspectid;
    }

    public String getInspectname() {
        return inspectname;
    }

    public void setInspectname(String inspectname) {
        this.inspectname = inspectname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(Double totalPay) {
        this.totalPay = totalPay;
    }
}
