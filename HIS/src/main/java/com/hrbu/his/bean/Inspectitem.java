package com.hrbu.his.bean;

/**
 * @ClassName: Inspectitem
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/04 16:47
 */
public class Inspectitem {
    private Integer id;
    private String inspectname;
    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
