package com.hrbu.his.service;

import com.hrbu.his.bean.Medicalrecord;

/**
 * @ClassName: MedicalrecordService
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/28 17:13
 */
public interface MedicalrecordService {
    Medicalrecord getMedicalrecordByPid(Integer pid);

    boolean saveMedicalrecord(Medicalrecord medicalrecord);
}
