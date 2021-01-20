package com.hrbu.his.service;

import com.hrbu.his.bean.Dept;
import com.hrbu.his.bean.Doctor;

import java.util.List;

/**
 * @ClassName: DoctorService
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/20 14:11
 */
public interface DoctorService {
    List<Doctor> getDoctorListById(int deptid);

    List<Doctor> getDoctorList();

    Doctor getDoctorByDocId(Integer doctorid);
}
