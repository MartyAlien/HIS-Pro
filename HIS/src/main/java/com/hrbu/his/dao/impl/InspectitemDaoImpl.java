package com.hrbu.his.dao.impl;

import com.hrbu.his.bean.*;
import com.hrbu.his.dao.InspectitemDao;
import com.hrbu.his.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @ClassName: InspectitemDaoImpl
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/04 16:50
 */
public class InspectitemDaoImpl implements InspectitemDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Inspectitem> getInpsectItemList() {
        String sql="select * from inspectitem";
        try {
            List<Inspectitem> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Inspectitem.class));
            return query;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveInspectitemRecord(String pid, InspectitemBeanList inspectitemBeanList) {
        List<InspectitemBean> selectedInspectList = inspectitemBeanList.getSelectedInspectList();
        int num=0;
        if(selectedInspectList!=null&&selectedInspectList.size()>0){
            String sql="insert into inspectitemrecord (pid,inspectid,amount,paystatus) "+
                    "values (?,?,?,0)";
            for (InspectitemBean e : selectedInspectList) {
                try {
                    num+=jdbcTemplate.update(sql,Integer.parseInt(pid),e.getId(),e.getAmount());
                } catch (DataAccessException ex) {
                    ex.printStackTrace();
                    return false;
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
            return num==selectedInspectList.size();
        }
        return false;
    }
    @Override
    public boolean saveInspectitemRecord(String pid, List<InspectitemBean> selectedInspectList) {
        int num=0;
        if(selectedInspectList!=null&&selectedInspectList.size()>0){
            String sql="insert into inspectitemrecord (pid,inspectid,amount,paystatus) "+
                    "values (?,?,?,0)";
            for (InspectitemBean e : selectedInspectList) {
                try {
                    num+=jdbcTemplate.update(sql,Integer.parseInt(pid),e.getId(),e.getAmount());
                } catch (DataAccessException ex) {
                    ex.printStackTrace();
                    return false;
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
            return num==selectedInspectList.size();
        }
        return false;
    }

    @Override
    public List<UnpayVo> getUnpayVoList(String pid,int paystatus) {
        String sql="select p.pid,p.pname,ir.inspectid,ins.inspectname,ins.price,ir.amount,(ir.amount*ins.price) totalpay \n" +
                "from inspectitemrecord ir,patient p ,inspectitem ins \n" +
                "where ir.paystatus=? and p.pid=? and ir.inspectid=ins.id and ir.pid=p.pid;";
        try {
            List<UnpayVo> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UnpayVo.class), paystatus,pid);
            return query;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UnpayPatientVo> getUnpayPatientVoList(int paystatus) {
        /*获取未交费的人员名单*/
        String sql="select distinct p.pid,p.pname,d.dname,dept.deptname\n" +
                "from inspectitemrecord ir,patient p,doctor d,dept\n" +
                "where ir.paystatus=? and ir.pid=p.pid and p.deptid=dept.id and p.doctorid=d.id;";
        try {
            List<UnpayPatientVo> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UnpayPatientVo.class),paystatus);
            return query;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean setPaystatus(String pid, String inspectid) {
        String[] insId = inspectid.split(",");
        String sql="update inspectitemrecord set paystatus=1 where inspectid in (?) and pid=?;";
        try {
            int num=0;
            for (String id : insId) {
                num+=jdbcTemplate.update(sql, id, pid);
            }
            return num!=0;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<InspectitemBean> getSelectedInspectList(Integer pid) {
        String sql="select ins.id,ins.inspectname,ins.price,isr.amount,isr.paystatus\n" +
                "from inspectitemrecord isr,inspectitem ins\n" +
                "where isr.pid=? and isr.inspectid=ins.id;";
        try {
            return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(InspectitemBean.class),pid);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
