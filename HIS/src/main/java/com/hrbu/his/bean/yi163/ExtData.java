package com.hrbu.his.bean.yi163;

/**
 * @ClassName: ExtData
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/17 11:39
 */
public class ExtData {
     // 无症状感染者
    private Integer noSymptom; //无症状病例累计
    private Integer incrNoSymptom; //无症状病例新增

    public Integer getNoSymptom() {
        return noSymptom;
    }

    public void setNoSymptom(Integer noSymptom) {
        this.noSymptom = noSymptom;
    }

    public Integer getIncrNoSymptom() {
        return incrNoSymptom;
    }

    public void setIncrNoSymptom(Integer incrNoSymptom) {
        this.incrNoSymptom = incrNoSymptom;
    }
}
