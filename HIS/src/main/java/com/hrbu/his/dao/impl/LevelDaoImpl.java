package com.hrbu.his.dao.impl;

import com.hrbu.his.bean.Level;
import com.hrbu.his.dao.LevelDao;
import com.hrbu.his.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @ClassName: LevelDaoImpl
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/21 11:49
 */
public class LevelDaoImpl implements LevelDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Level> getLevelList() {
        try {
            return jdbcTemplate.query("select * from level",new BeanPropertyRowMapper<>(Level.class));
        } catch (DataAccessException e) {
            System.err.println("异常：LevelDaoImpl.class未能找到数据库中的数据");
            return null;
        }
    }

    @Override
    public Level getLevelBylevId(Integer levelid) {
        try {
            return jdbcTemplate.queryForObject("select * from level where id = ?",new BeanPropertyRowMapper<>(Level.class),levelid);
        } catch (DataAccessException e) {
            System.err.println("异常：LevelDaoImpl.class未能找到数据库中的数据");
            return null;
        }
    }
}
