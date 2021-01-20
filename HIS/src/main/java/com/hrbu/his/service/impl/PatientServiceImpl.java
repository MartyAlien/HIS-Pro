package com.hrbu.his.service.impl;

import com.hrbu.his.bean.Patient;
import com.hrbu.his.dao.PatientDao;
import com.hrbu.his.dao.impl.PatientDaoImpl;
import com.hrbu.his.service.PatientService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName: PatientServiceImpl
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/21 17:08
 */
public class PatientServiceImpl implements PatientService {
    private PatientDao patientDao=new PatientDaoImpl();
    @Override
    public Integer savePatient(Patient patient) {
        // 初识化一部分属性
        if(patient!=null){
            patient.setPstatus(1+"");
            patient.setStatus(1);
            patient.setOperatedate(LocalDateTime.now());
            patient.setOperator(1);
        }else {
            return 0;
        }
        return patientDao.savePatient(patient);
    }

    @Override
    public List<Patient> getPatientListPage(String page, String pageSize) {
        return patientDao.getPatientListPage(page,pageSize);
    }

    @Override
    public int getPatientTotalNum() {
        return patientDao.getPatientTotalNum();
    }

    @Override
    public boolean backnoPatient(String pid) {
        return patientDao.backnoPatient(pid);
    }

    @Override
    public List<Patient> getBackableListPage(String page, String pageSize) {
        return patientDao.getBackableListPage(page,pageSize);
    }

    @Override
    public List<Patient> getPatientListByPatient(Patient patient) {
        return patientDao.getPatientListByPatient(patient);
    }

    @Override
    public boolean updatePatientPstatus(String pid, String pstatus) {
        return patientDao.updatePatientPstatus(pid,pstatus);
    }
}
