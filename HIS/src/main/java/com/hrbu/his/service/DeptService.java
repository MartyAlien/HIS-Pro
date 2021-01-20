package com.hrbu.his.service;

import com.hrbu.his.bean.Dept;

import java.util.List;

/**
 * @ClassName: DeptService
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/20 13:12
 */
public interface DeptService {
    List<Dept> getAllDept();

    Dept getDeptById(Integer deptid);
}
