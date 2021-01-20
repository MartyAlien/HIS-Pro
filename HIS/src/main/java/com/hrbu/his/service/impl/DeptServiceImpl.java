package com.hrbu.his.service.impl;

import com.hrbu.his.bean.Dept;
import com.hrbu.his.dao.DeptDao;
import com.hrbu.his.dao.impl.DeptDaoImpl;
import com.hrbu.his.service.DeptService;

import java.util.List;

/**
 * @ClassName: DeptServiceImpl
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/20 13:12
 */
public class DeptServiceImpl implements DeptService {
    private DeptDao deptDao=new DeptDaoImpl();
    @Override
    public List<Dept> getAllDept() {
        return deptDao.getAllDept();
    }

    @Override
    public Dept getDeptById(Integer deptid) {
        return deptDao.getDeptById(deptid);
    }
}
