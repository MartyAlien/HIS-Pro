package com.hrbu.his.dao.impl;

import com.hrbu.his.bean.Dept;
import com.hrbu.his.dao.DeptDao;
import com.hrbu.his.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @ClassName: DeptDaoImpl
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/20 13:15
 */
public class DeptDaoImpl implements DeptDao {
    private JdbcTemplate jdbcTemplate= new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Dept> getAllDept() {
        try {
            return jdbcTemplate.query("select * from dept", new BeanPropertyRowMapper<>(Dept.class));
        } catch (DataAccessException e) {
            System.err.println("异常：DeptDaoImpl.class未能找到数据库中的数据");
            return null;
        }
    }

    @Override
    public Dept getDeptById(Integer deptid) {
        try {
            return jdbcTemplate.queryForObject("select * from dept where id=?", new BeanPropertyRowMapper<>(Dept.class),deptid);
        } catch (DataAccessException e) {
            System.err.println("异常：DeptDaoImpl.class---getDeptById未能找到数据库中的数据");
            return null;
        }
    }
}
