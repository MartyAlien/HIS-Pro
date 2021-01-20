package com.hrbu.his.dao;

import com.hrbu.his.bean.Level;

import java.util.List;

/**
 * @ClassName: LevelDao
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/21 11:49
 */
public interface LevelDao {
    List<Level> getLevelList();

    Level getLevelBylevId(Integer levelid);
}
