package com.hrbu.his.request;


import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Result
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/15 14:52
 */
public class Result {
    private Integer code;
    private String message;
    private Map<String,Object> data;

    public Result() {
    }

    public Result(Integer code,String message) {
        this.code = code;
        this.message=message;
        this.data=new HashMap<>();
    }

    public Result(Integer code, String message, Map<String, Object> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public static Result ok(){
        return new Result(20000,"成功");
    }
    public static Result error(){
        return new Result(10000,"失败");
    }
    public static Result error(String msg){
        return new Result(10000,msg==null?"失败":msg);
    }

    public Result data(String key,Object obj){
        this.getData().put(key,obj);
        return this;
    }
    public Result data(Map<String,Object> data){
        this.setData(data);
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
