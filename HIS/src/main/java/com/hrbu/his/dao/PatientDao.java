package com.hrbu.his.dao;

import com.hrbu.his.bean.Patient;

import java.util.List;

/**
 * @ClassName: PatientDao
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/21 17:10
 */
public interface PatientDao {
    Integer savePatient(Patient patient);

    List<Patient> getPatientListPage(String page, String pageSize);

    int getPatientTotalNum();

    boolean backnoPatient(String pid);

    List<Patient> getBackableListPage(String page, String pageSize);

    List<Patient> getPatientListByPatient(Patient patient);

    boolean updatePatientPstatus(String pid, String pstatus);
}
