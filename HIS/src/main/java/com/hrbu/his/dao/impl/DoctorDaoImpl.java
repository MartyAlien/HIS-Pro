package com.hrbu.his.dao.impl;

import com.hrbu.his.bean.Dept;
import com.hrbu.his.bean.Doctor;
import com.hrbu.his.dao.DoctorDao;
import com.hrbu.his.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @ClassName: DoctorDaoImpl
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/20 14:14
 */
public class DoctorDaoImpl implements DoctorDao {
    private JdbcTemplate jdbcTemplate= new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Doctor> getDoctorListById(int deptid) {
        try {
            return jdbcTemplate.query("select * from doctor where deptid=?",new BeanPropertyRowMapper<>(Doctor.class),deptid);
        } catch (DataAccessException e) {
            System.err.println("异常：DoctorDaoImpl.class未能找到数据库中的数据");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Doctor> getDoctorList() {
        try {
            return jdbcTemplate.query("select * from doctor",new BeanPropertyRowMapper<>(Doctor.class));
        } catch (DataAccessException e) {
            System.err.println("异常：DoctorDaoImpl.class未能找到数据库中的数据");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Doctor getDoctorByDocId(Integer doctorid) {
        try {
            return jdbcTemplate.queryForObject("select * from doctor where id=?",new BeanPropertyRowMapper<>(Doctor.class),doctorid);
        } catch (DataAccessException e) {
            System.err.println("异常：DoctorDaoImpl.class未能找到数据库中的数据");
            e.printStackTrace();
            return null;
        }
    }
}
