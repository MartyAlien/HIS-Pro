package com.hrbu.his.service.impl;

import com.hrbu.his.bean.Dept;
import com.hrbu.his.bean.Doctor;
import com.hrbu.his.dao.DoctorDao;
import com.hrbu.his.dao.impl.DoctorDaoImpl;
import com.hrbu.his.service.DoctorService;

import java.util.List;

/**
 * @ClassName: DoctorServiceImpl
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/20 14:11
 */
public class DoctorServiceImpl implements DoctorService {
    private DoctorDao doctorDao=new DoctorDaoImpl();
    @Override
    public List<Doctor> getDoctorListById(int deptid) {
        return doctorDao.getDoctorListById(deptid);
    }

    @Override
    public List<Doctor> getDoctorList() {
        return doctorDao.getDoctorList();
    }

    @Override
    public Doctor getDoctorByDocId(Integer doctorid) {
        return doctorDao.getDoctorByDocId(doctorid);
    }
}
