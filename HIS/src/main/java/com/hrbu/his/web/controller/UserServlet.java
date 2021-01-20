package com.hrbu.his.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrbu.his.bean.User;
import com.hrbu.his.request.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ${NAME}
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/16 11:30
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    public Result login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = acceptJson(req);
        if(s!=null){
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(s, User.class);
        }
        return Result.ok().data("token","admin-token");
    }
    public Result logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = acceptJson(req);
        if(s!=null){
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(s, User.class);
        }
        return Result.ok();
    }
    public Result info(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        Map<String, Object> map = new HashMap<>();
        // 根据token获取到数据库中相关用户信息
        map.put("roles", Arrays.asList("admin"));
        map.put("introduction", Arrays.asList("https://wping.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"));
        map.put("avatar", "cerga");
        map.put("name", "赏金猎人");
        return Result.ok().data(map);
    }
}
