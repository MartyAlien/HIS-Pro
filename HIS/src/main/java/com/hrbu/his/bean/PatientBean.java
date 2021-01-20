package com.hrbu.his.bean;

/**
 * @ClassName: PatientBean
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/28 14:29
 */
public class PatientBean {
    private Patient patient;
    private Medicalrecord medicalrecord;
    private Doctor doctor;
    private Dept dept;
    private Level level;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medicalrecord getMedicalrecord() {
        return medicalrecord;
    }

    public void setMedicalrecord(Medicalrecord medicalrecord) {
        this.medicalrecord = medicalrecord;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
}
