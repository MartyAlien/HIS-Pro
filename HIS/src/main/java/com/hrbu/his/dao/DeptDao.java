package com.hrbu.his.dao;

import com.hrbu.his.bean.Dept;

import java.util.List;

/**
 * @ClassName: DeptDao
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/20 13:15
 */
public interface DeptDao {
    List<Dept> getAllDept();

    Dept getDeptById(Integer deptid);
}
