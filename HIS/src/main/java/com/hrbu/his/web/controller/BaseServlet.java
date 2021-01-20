package com.hrbu.his.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrbu.his.request.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ClassName: BaseServlet
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/16 11:29
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 根据请求路径获取方法
            req.setCharacterEncoding("utf-8");
            resp.setContentType("text/json;charset=utf-8");
            String requestURI = req.getRequestURI();
            System.out.println("URI: " + requestURI);
            String methodURI = requestURI.substring(requestURI.lastIndexOf("/") + 1);
            // 反射到对应的方法
            Method method = this.getClass().getMethod(methodURI, HttpServletRequest.class, HttpServletResponse.class);
            // 执行方法
            Result result = (Result) method.invoke(this,req, resp);
            // 将结果转json
            if(result!=null){
                printJavaToJson(result,resp);
            }else {
                System.out.println("result为null");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public void printJavaToJson(Result result,HttpServletResponse resp) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getOutputStream(),result);
    }

    public String acceptJson(HttpServletRequest request) throws IOException {
        // 获取请求输入流
        InputStreamReader ins = new InputStreamReader(request.getInputStream(), "utf-8");
        BufferedReader br = new BufferedReader(ins);
        StringBuffer sb = new StringBuffer();
        String tmp="";
        while ((tmp=br.readLine())!=null){
            sb.append(tmp);
        }
        br.close();
        System.out.println("json: "+sb.toString());
        if(sb.equals("") || sb.length()==0){
            // 获取参数失败的话
            return null;
        }
        return sb.toString();
    }
}
