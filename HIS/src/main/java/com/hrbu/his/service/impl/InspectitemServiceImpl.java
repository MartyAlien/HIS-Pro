package com.hrbu.his.service.impl;

import com.hrbu.his.bean.*;
import com.hrbu.his.dao.InspectitemDao;
import com.hrbu.his.dao.impl.InspectitemDaoImpl;
import com.hrbu.his.service.InspectitemService;

import java.util.List;

/**
 * @ClassName: InspectitemServiceImpl
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/04 16:46
 */
public class InspectitemServiceImpl implements InspectitemService {
    private InspectitemDao inspectitemDao=new InspectitemDaoImpl();
    @Override
    public List<Inspectitem> getInpsectItemList() {
        return inspectitemDao.getInpsectItemList();
    }

    @Override
    public boolean saveInspectitemRecord(String pid, InspectitemBeanList inspectitemBeanList) {
        return inspectitemDao.saveInspectitemRecord(pid,inspectitemBeanList);
    }
    @Override
    public boolean saveInspectitemRecord(String pid, List<InspectitemBean> selectedInspectList) {
        return inspectitemDao.saveInspectitemRecord(pid,selectedInspectList);
    }
    @Override
    public List<UnpayVo> getUnpayVoList(String pid,int paystatus) {
        return inspectitemDao.getUnpayVoList(pid,paystatus);
    }

    @Override
    public List<UnpayPatientVo> getUnpayPatientVoList(int paystatus) {
        return inspectitemDao.getUnpayPatientVoList(paystatus);
    }

    @Override
    public boolean setPaystatus(String pid, String inspectid) {
        return inspectitemDao.setPaystatus(pid,inspectid);
    }

    @Override
    public List<InspectitemBean> getSelectedInspectList(Integer pid) {
        return inspectitemDao. getSelectedInspectList(pid);
    }
}
