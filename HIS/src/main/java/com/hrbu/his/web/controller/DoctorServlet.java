package com.hrbu.his.web.controller;

import com.hrbu.his.bean.Doctor;
import com.hrbu.his.request.Result;
import com.hrbu.his.service.DoctorService;
import com.hrbu.his.service.impl.DoctorServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

/**
 * @ClassName: DoctorServlet
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/20 14:08
 */
@WebServlet("/doctor/*")
public class DoctorServlet extends BaseServlet {
   private DoctorService doctorService= new DoctorServiceImpl();
    public Result getDoctorList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 根据选中的科室来获取对应科室中的医生
        int deptid = Integer.parseInt(req.getParameter("deptid"));
        List<Doctor> doctorList = doctorService.getDoctorListById(deptid);
        return Result.ok().data("doctorList",doctorList);
    }
}
