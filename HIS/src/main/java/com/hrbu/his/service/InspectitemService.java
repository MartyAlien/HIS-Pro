package com.hrbu.his.service;

import com.hrbu.his.bean.*;

import java.util.List;

/**
 * @ClassName: InspectitemService
 * @Author: Lemo-TanYajun
 * @CreateTime: 2021/01/04 16:45
 */
public interface InspectitemService {
    List<Inspectitem> getInpsectItemList();

    boolean saveInspectitemRecord(String pid, InspectitemBeanList inspectitemBeanList);

    boolean saveInspectitemRecord(String pid, List<InspectitemBean> selectedInspectList);

    List<UnpayVo> getUnpayVoList(String pid, int paystatus);

    List<UnpayPatientVo> getUnpayPatientVoList(int paystatus);

    boolean setPaystatus(String pid, String inspectid);

    List<InspectitemBean> getSelectedInspectList(Integer pid);
}
