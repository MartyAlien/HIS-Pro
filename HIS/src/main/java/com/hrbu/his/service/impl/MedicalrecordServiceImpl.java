package com.hrbu.his.service.impl;

import com.hrbu.his.bean.Medicalrecord;
import com.hrbu.his.dao.MedicalrecordDao;
import com.hrbu.his.dao.impl.MedicalrecordDaoImpl;
import com.hrbu.his.service.MedicalrecordService;

/**
 * @ClassName: MedicalrecordServiceImpl
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/28 17:13
 */
public class MedicalrecordServiceImpl implements MedicalrecordService {
    private MedicalrecordDao medicalrecordDao=new MedicalrecordDaoImpl();
    @Override
    public Medicalrecord getMedicalrecordByPid(Integer pid) {
        return medicalrecordDao.getMedicalrecordByPid(pid);
    }

    @Override
    public boolean saveMedicalrecord(Medicalrecord medicalrecord) {
        return medicalrecordDao.saveMedicalrecord(medicalrecord);
    }
}
