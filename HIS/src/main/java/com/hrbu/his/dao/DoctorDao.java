package com.hrbu.his.dao;

import com.hrbu.his.bean.Dept;
import com.hrbu.his.bean.Doctor;

import java.util.List;

/**
 * @ClassName: DoctorDao
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/20 14:14
 */
public interface DoctorDao {
    List<Doctor> getDoctorListById(int deptid);

    List<Doctor> getDoctorList();

    Doctor getDoctorByDocId(Integer doctorid);
}
