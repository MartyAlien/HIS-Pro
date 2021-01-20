package com.hrbu.his.dao.impl;

import com.hrbu.his.bean.Patient;
import com.hrbu.his.dao.PatientDao;
import com.hrbu.his.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @ClassName: PatientDaoImpl
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/21 17:10
 */
public class PatientDaoImpl implements PatientDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Integer savePatient(Patient patient) {
        try {
            String sql="INSERT INTO `his`.`patient`(`pid`, `pname`, `sex`, `age`, `birthday`, " +
                    "`idcard`, `address`, `levelid`, `deptid`, `doctorid`, `createdate`, " +
                    "`cost`, `pstatus`, `status`, `operator`, `operatedate`) " +
                    "VALUES (?, ?, ?, ?, NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            int update = jdbcTemplate.update(sql, patient.getPid(),
                    patient.getPname(), patient.getSex(), patient.getAge(), patient.getIdcard(),
                    patient.getAddress(), patient.getLevelid(), patient.getDeptid(), patient.getDoctorid(),
                    patient.getCreatedate(), patient.getCost(), patient.getPstatus(), patient.getStatus(),
                    patient.getOperator(), patient.getOperatedate());
            return update;
        } catch (DataAccessException e) {
            System.err.println("异常：PatientDaoImpl.class未能找到数据库中的数据");
            return 0;
        }
    }

    @Override
    public List<Patient> getPatientListPage(String page, String pageSize) {
        try {
            int currentPage = Integer.parseInt(page);
            int size = Integer.parseInt(pageSize);
            int x=(currentPage-1)*size;
            String sql="select * from patient order by operatedate desc limit ?,?";
            List<Patient> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Patient.class), x, size);
            return list;
        } catch (Exception e) {
            System.err.println("异常：PatientDaoImpl.class的getPatientListPage方法有点毛病");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Patient> getBackableListPage(String page, String pageSize) {
        try {
            int currentPage = Integer.parseInt(page);
            int size = Integer.parseInt(pageSize);
            int x=(currentPage-1)*size;
            String sql="select * from patient where pstatus <> '0' order by operatedate desc limit ?,?";
            List<Patient> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Patient.class), x, size);
            return list;
        } catch (Exception e) {
            System.err.println("异常：PatientDaoImpl.class的getBackableListPage方法有点毛病");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Patient> getPatientListByPatient(Patient patient) {
        String sql=null;
        if(patient==null){
            return null;
        }
        if("1".equals(patient.getPstatus())||"0".equals(patient.getPstatus())){
            sql="select * from patient where pstatus=? order by createdate "+("1".equals(patient.getPstatus())?" asc ":" desc ");
            try {
                return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Patient.class),patient.getPstatus());
            } catch (DataAccessException e) {
                System.err.println("异常：PatientDaoImpl.class的getPatientListByPatient方法有点毛病");
                e.printStackTrace();
                return null;
            }
        }
        if(patient.getPid()!=null&&!"".equals(patient.getPid()+"")){
            sql="select * from patient where pid=?";
            try {
                return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Patient.class),patient.getPid());
            } catch (DataAccessException e) {
                System.err.println("异常：PatientDaoImpl.class的getPatientListByPatient方法有点毛病");
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    @Override
    public boolean updatePatientPstatus(String pid, String pstatus) {
        String sql="update patient set pstatus=? where pid=?";
        try {
            return jdbcTemplate.update(sql,pstatus,Integer.parseInt(pid))==1;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getPatientTotalNum() {
        try {
            Integer total = jdbcTemplate.queryForObject("select count(*) from patient", Integer.class);
            return total==null?0:total;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean backnoPatient(String pid) {
        String sql="update patient set pstatus='2' where pid=? and pstatus='1'";
        try {
            int update = jdbcTemplate.update(sql, pid);
            System.out.println("退号操作：update=" + update);
            return update==1;
        } catch (DataAccessException e) {
            System.err.println("异常：PatientDaoImpl.class的backnoPatient方法有点毛病");
            e.printStackTrace();
            return false;
        }
    }

}
