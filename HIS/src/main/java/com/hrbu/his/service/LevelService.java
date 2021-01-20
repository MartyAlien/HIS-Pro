package com.hrbu.his.service;

import com.hrbu.his.bean.Level;

import java.util.List;

/**
 * @ClassName: LevelService
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/21 11:47
 */
public interface LevelService {
    List<Level> getLevelList();

    Level getLevelBylevId(Integer levelid);
}
