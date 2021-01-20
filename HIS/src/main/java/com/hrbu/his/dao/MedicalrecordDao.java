package com.hrbu.his.dao;

import com.hrbu.his.bean.Medicalrecord;

/**
 * @ClassName: MedicalrecordDao
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/28 17:15
 */
public interface MedicalrecordDao {
    Medicalrecord getMedicalrecordByPid(Integer pid);

    boolean saveMedicalrecord(Medicalrecord medicalrecord);
}
