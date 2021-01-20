package com.hrbu.his.service.impl;

import com.hrbu.his.bean.Level;
import com.hrbu.his.dao.LevelDao;
import com.hrbu.his.dao.impl.LevelDaoImpl;
import com.hrbu.his.service.LevelService;

import java.util.List;

/**
 * @ClassName: LevelServiceImpl
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/21 11:47
 */
public class LevelServiceImpl implements LevelService {
    private LevelDao levelDao=new LevelDaoImpl();
    @Override
    public List<Level> getLevelList() {
        return levelDao.getLevelList();
    }

    @Override
    public Level getLevelBylevId(Integer levelid) {
        return levelDao.getLevelBylevId(levelid);
    }
}
