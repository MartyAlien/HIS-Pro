package com.hrbu.his.dao.impl;

import com.hrbu.his.bean.Medicalrecord;
import com.hrbu.his.dao.MedicalrecordDao;
import com.hrbu.his.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

/**
 * @ClassName: MedicalrecordDaoImpl
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/28 17:15
 */
public class MedicalrecordDaoImpl implements MedicalrecordDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Medicalrecord getMedicalrecordByPid(Integer pid) {
        try {
            return jdbcTemplate.queryForObject("select * from medicalrecord where pid=?",
                    new BeanPropertyRowMapper<>(Medicalrecord.class),pid);
        } catch (EmptyResultDataAccessException e){
            System.err.println("待诊患者的病例表还未存在信息-空resultset");
            return new Medicalrecord();
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveMedicalrecord(Medicalrecord medicalrecord) {
        if(medicalrecord!=null){
            String sql="insert into medicalrecord (pid,description,medicalhistory,familyhistory,initialresult,result,finalresult,status,operator,operatedate) "+
                    "values (?,?,?,?,?,?,?,?,?,?) ";
            try {
                int update = jdbcTemplate.update(sql, medicalrecord.getPid(),
                        medicalrecord.getDescription(), medicalrecord.getMedicalhistory(),
                        medicalrecord.getFamilyhistory(), medicalrecord.getInitialresult(),
                        medicalrecord.getResult(),medicalrecord.getFinalresult(),
                        medicalrecord.getStatus() == null ? 1 : medicalrecord.getStatus(),
                        medicalrecord.getOperator() == null ? 1 : medicalrecord.getOperator(),
                        medicalrecord.getOperatedate() == null ? LocalDateTime.now() : medicalrecord.getOperatedate());
                return update==1;
            } catch (DataIntegrityViolationException e){
                System.err.println("MedicalrecordDaoImpl.class下的saveMedicalrecord出现DataIntegrityViolationException异常");
                return false;
            } catch (DataAccessException e) {
                System.err.println("MedicalrecordDaoImpl.class下的saveMedicalrecord出现DataAccessException异常");
                return false;
            }
        }
        return false;
    }
}
