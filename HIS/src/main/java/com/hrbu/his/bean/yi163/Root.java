package com.hrbu.his.bean.yi163;

/**
 * @ClassName: Root
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/17 11:58
 */
public class Root {
    private String reqId;
    private Integer code;
    private String msg;
    private Data data;
    private Long timestamp;

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
