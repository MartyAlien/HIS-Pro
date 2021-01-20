package com.hrbu.his.bean;

/**
 * @ClassName: InspectitemBean
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/05 19:47
 */
public class InspectitemBean extends Inspectitem{
    private Integer amount;
    private Integer paystatus;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(Integer paystatus) {
        this.paystatus = paystatus;
    }
}
