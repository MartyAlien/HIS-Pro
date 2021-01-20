package com.hrbu.his.dao;

import com.hrbu.his.bean.*;

import java.util.List;

/**
 * @ClassName: InspectitemDao
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/04 16:49
 */
public interface InspectitemDao {
    List<Inspectitem> getInpsectItemList();

    boolean saveInspectitemRecord(String pid, InspectitemBeanList inspectitemBeanList);

    boolean saveInspectitemRecord(String pid, List<InspectitemBean> selectedInspectList);

    List<UnpayVo> getUnpayVoList(String pid, int paystatus);

    List<UnpayPatientVo> getUnpayPatientVoList(int paystatus);

    boolean setPaystatus(String pid, String inspectid);

    List<InspectitemBean> getSelectedInspectList(Integer pid);
}
