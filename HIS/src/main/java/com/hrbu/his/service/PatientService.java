package com.hrbu.his.service;

import com.hrbu.his.bean.Patient;

import java.util.List;

/**
 * @ClassName: PatientService
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/21 17:08
 */
public interface PatientService {
    Integer savePatient(Patient patient);

    List<Patient> getPatientListPage(String page, String pageSize);

    int getPatientTotalNum();

    boolean backnoPatient(String pid);

    List<Patient> getBackableListPage(String page, String pageSize);

    List<Patient> getPatientListByPatient(Patient patient);

    boolean updatePatientPstatus(String pid, String pstatus);
}
