package com.hrbu.his.web.controller;

import com.hrbu.his.bean.Doctor;
import com.hrbu.his.bean.Level;
import com.hrbu.his.request.Result;
import com.hrbu.his.service.LevelService;
import com.hrbu.his.service.impl.LevelServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName: LevelServlet
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/21 11:45
 */
@WebServlet("/level/*")
public class LevelServlet extends BaseServlet {
    private LevelService levelService=new LevelServiceImpl();
    public Result getLevelList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取诊疗级别
        List<Level> levelList = levelService.getLevelList();
        return Result.ok().data("levelList",levelList);
    }
}
