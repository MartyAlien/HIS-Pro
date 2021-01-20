package com.hrbu.his.web.controller;

import com.hrbu.his.bean.Dept;
import com.hrbu.his.request.Result;
import com.hrbu.his.service.DeptService;
import com.hrbu.his.service.impl.DeptServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

/**
 * @ClassName: DeptServlet
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/20 13:09
 */
@WebServlet("/dept/*")
public class DeptServlet extends BaseServlet {
    private DeptService deptService=new DeptServiceImpl();
    public Result getAllDeptList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
        * @Description 用于返回科室列表
        * @param
         * @param req
         * @param resp
        * @return com.hrbu.his.request.Result
        * @date 2020/12/20 0020 13:10
        * @author Lemo-TanYajun
        */
        //获取科室列表
        List<Dept> allDeptList=deptService.getAllDept();
        if (allDeptList==null){
            return Result.error();
        }
        return Result.ok().data("allDeptList",allDeptList);
    }
}
