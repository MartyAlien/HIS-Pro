package com.hrbu.his.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrbu.his.bean.*;
import com.hrbu.his.request.Result;
import com.hrbu.his.service.*;
import com.hrbu.his.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @ClassName: PatientServlet
 * @Author: Lemo-TanYajun
 * @CreateTime: 2020/12/20 12:57
 */
@WebServlet("/patient/*")
public class PatientServlet extends BaseServlet {
    private PatientService patientService=new PatientServiceImpl();
    private DeptService deptService=new DeptServiceImpl();
    private DoctorService doctorService= new DoctorServiceImpl();
    private LevelService levelService=new LevelServiceImpl();
    private MedicalrecordService medicalrecordService=new MedicalrecordServiceImpl();
    private InspectitemService inspectitemService=new InspectitemServiceImpl();
    private static Random random = new Random();
    /**
    * @Description 根据时间戳产生pid用于病历号
    * @param
     * @param req
     * @param resp
    * @return com.hrbu.his.request.Result
    * @date 2020/12/28 0028 09:25
    * @author Lemo-TanYajun
    */
    public Result getPid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过时间戳产生病历号
        char[] arr= (Instant.now().toEpochMilli()+"").toCharArray();
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < 9; i++) {
            sb.append(arr[random.nextInt(arr.length)]);
        }
        return Result.ok().data("pid",Integer.parseInt(sb.toString()));
    }

    /**
    * @Description 将挂号患者信息保存到数据库
    * @param
     * @param req
     * @param resp
    * @return com.hrbu.his.request.Result
    * @date 2020/12/28 0028 09:24
    * @author Lemo-TanYajun
    */
    public Result saveRegisteredInDB(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //将前台的挂号信息保存进数据库，并且返回响应信息
        String s = acceptJson(req);
        if(s!=null){
            ObjectMapper objectMapper = new ObjectMapper();
            Patient patient = objectMapper.readValue(s, Patient.class);
            Integer i=patientService.savePatient(patient);
            if(i!=null&&i==1){
                return Result.ok();
            }else {
                return Result.error("挂号失败，请联系开发人员");
            }
        }
        return Result.error();
    }


    /**
    * @Description 获取分页数据列表
    * @param
     * @param req
     * @param resp
    * @return com.hrbu.his.request.Result
    * @date 2020/12/28 0028 09:23
    * @author Lemo-TanYajun
    */
    public Result getPatientListPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 根据页面的容量和当前页面获取用户数据
        String page=null;
        String pageSize=null;
        try {
            page=req.getParameter("page");
        } catch (Exception e) {
            page=null;
        }
        page=page==null?"1":page;
        try {
            pageSize=req.getParameter("pageSize");
        } catch (Exception e) {
            pageSize=null;
        }
        pageSize=pageSize==null?"5":pageSize;
        // 获取患者列表
        List<Patient> patientList=patientService.getPatientListPage(page,pageSize);
        List<Dept> allDept = deptService.getAllDept();
        List<Doctor> docList=doctorService.getDoctorList();

        for (int i = 0; i < patientList.size(); i++) {
            for (Dept dept : allDept) {
                if(dept.getId()==patientList.get(i).getDeptid()){
                    patientList.get(i).setDeptname(dept.getDeptname());
                    break;
                }
            }
            for (Doctor doctor : docList) {
                if(doctor.getId()==patientList.get(i).getDoctorid()){
                    patientList.get(i).setDname(doctor.getDname());
                    break;
                }
            }
        }
        int total=patientService.getPatientTotalNum();
        return Result.ok().data("patientList",patientList).data("total",total);
    }

    /**
    * @Description 根据pid进行模糊查询
    * @param
     * @param req
     * @param resp
    * @return com.hrbu.his.request.Result
    * @date 2020/12/28 0028 09:23
    * @author Lemo-TanYajun
    */
    public Result getPatientListPageLikePid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 从未退号的列表中找出包含该pid的患者
        Result result = getBackableListPage(req, resp);
        Map<String, Object> data = result.getData();
        List<Patient>  patientList = (List<Patient>) data.get("patientList");
        String pid = null;
        try {
            pid=req.getParameter("pid");
        } catch (Exception e) {
            pid=null;
        }
        if(pid==null){
            return Result.ok().data("code","20001");
        }else {
            String finalPid = pid;
            List<Patient> pList = null;
            try {
                pList=patientList.stream().filter(p -> (p.getPid() + "").contains(finalPid)).collect(Collectors.toList());
                if(pList!=null&&pList.size()>0){
                    return Result.ok().data("code","20000").data("patientList",pList).data("total",pList.size());
                }else {
                    return Result.ok().data("code","20002");
                }
            } catch (Exception e) {
                return Result.ok().data("code","20002");
            }

        }
    }
    /**
    * @Description 退号用户数据分页
    * @param
     * @param req
     * @param resp
    * @return com.hrbu.his.request.Result
    * @date 2020/12/28 0028 09:26
    * @author Lemo-TanYajun
    */
    public Result getBackableListPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 根据页面的容量和当前页面获取用户数据
        String page=null;
        String pageSize=null;
        try {
            page=req.getParameter("page");
        } catch (Exception e) {
            page=null;
        }
        page=page==null?"1":page;
        try {
            pageSize=req.getParameter("pageSize");
        } catch (Exception e) {
            pageSize=null;
        }
        pageSize=pageSize==null?"5":pageSize;
        // 获取患者列表
        List<Patient> patientList=patientService.getBackableListPage(page,pageSize);
        List<Dept> allDept = deptService.getAllDept();
        List<Doctor> docList=doctorService.getDoctorList();

        for (int i = 0; i < patientList.size(); i++) {
            for (Dept dept : allDept) {
                if(dept.getId()==patientList.get(i).getDeptid()){
                    patientList.get(i).setDeptname(dept.getDeptname());
                    break;
                }
            }
            for (Doctor doctor : docList) {
                if(doctor.getId()==patientList.get(i).getDoctorid()){
                    patientList.get(i).setDname(doctor.getDname());
                    break;
                }
            }
        }
        int total=patientList.size();
        return Result.ok().data("patientList",patientList).data("total",total);
    }
    /**
    * @Description 为患者退号
    * @param
     * @param req
     * @param resp
    * @return com.hrbu.his.request.Result
    * @date 2020/12/28 0028 09:27
    * @author Lemo-TanYajun
    */
    public Result setBacknoPatient(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //退号操作
        String pid = null;
        try {
            pid=req.getParameter("pid");
        } catch (Exception e) {
            pid=null;
        }
        if(pid==null){
            return Result.ok().data("code",10000);
        }
        boolean isBackno=patientService.backnoPatient(pid);
        if(isBackno){
            return Result.ok().data("code",20000);
        }else {
            return Result.ok().data("code",10001);
        }
    }
    public Result getUnTreatmentPatientList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Patient patient = new Patient();
        patient.setPstatus("1");
        List<Patient> unTreatmentPatientList=patientService.getPatientListByPatient(patient);
        if(unTreatmentPatientList==null){
            return Result.ok().data("code","10001");
        }
        if(unTreatmentPatientList.size()<1){
            return Result.ok().data("code","10002");
        }else {
            return Result.ok().data("code","20000").data("unTreatmentPatientList",unTreatmentPatientList);
        }
    }
    public Result getTreatedmentPatientList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Patient patient = new Patient();
        patient.setPstatus("0");
        List<Patient> treatedmentPatientList=patientService.getPatientListByPatient(patient);
        if(treatedmentPatientList==null){
            return Result.ok().data("code","10001");
        }
        if(treatedmentPatientList.size()<1){
            return Result.ok().data("code","10002");
        }else {
            return Result.ok().data("code","20000").data("treatedmentPatientList",treatedmentPatientList);
        }
    }

    /**
    * @Description 医生工作站：获取封装的患者信息
    * @param
     * @param req
     * @param resp
    * @return com.hrbu.his.request.Result
    * @date 2021/1/8 0008 16:15
    * @author Lemo-TanYajun
    */
    public Result getPatientBean(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = null;
        try {
            pid=req.getParameter("pid");
        } catch (Exception e) {
            pid=null;
        }
        if(pid==null||pid.equals("")){
            return Result.ok().data("code","10000");
        }

        Patient patient = new Patient();
        patient.setPid(Integer.parseInt(pid));
        List<Patient> temp=patientService.getPatientListByPatient(patient);
        if(temp==null){
            return Result.ok().data("code","10001");
        }
        if(temp.size()<1){
            return Result.ok().data("code","10002");
        }
        Patient p = temp.get(0);
        Dept dept=deptService.getDeptById(p.getDeptid());
        Doctor doctor = doctorService.getDoctorByDocId(p.getDoctorid());
        Level level=levelService.getLevelBylevId(p.getLevelid());
        Medicalrecord medicalrecord=medicalrecordService.getMedicalrecordByPid(p.getPid());
        PatientBean patientBean = new PatientBean();
        patientBean.setPatient(p);
        patientBean.setDept(dept);
        patientBean.setDoctor(doctor);
        patientBean.setLevel(level);
        patientBean.setMedicalrecord(medicalrecord);
        return Result.ok().data("code","20000").data("patientBean",patientBean);
    }

    /**
    * @Description 根据前台双击传过来的pid获取当前选中患者的所有就诊数据
    * @param
     * @param req
     * @param resp
    * @return com.hrbu.his.request.Result
    * @date 2021/1/8 0008 16:17
    * @author Lemo-TanYajun
    */
    public Result getCurrentAllBeanRow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = null;
        try {
            pid=req.getParameter("pid");
        } catch (Exception e) {
            pid=null;
        }
        if(pid==null||pid.equals("")){
            return Result.ok().data("code","10000").data("msg","pid获取为null或为空串");
        }

        Patient patient = new Patient();
        patient.setPid(Integer.parseInt(pid));
        List<Patient> temp=patientService.getPatientListByPatient(patient);
        if(temp==null){
            return Result.ok().data("code","10001").data("msg","根据pid未能成功获取对应患者数据 ");
        }
        if(temp.size()<1){
            return Result.ok().data("code","10002").data("msg","根据pid获取到的数据为0");
        }
        Patient p = temp.get(0);
        Dept dept=deptService.getDeptById(p.getDeptid());
        Doctor doctor = doctorService.getDoctorByDocId(p.getDoctorid());
        Level level=levelService.getLevelBylevId(p.getLevelid());
        Medicalrecord medicalrecord=medicalrecordService.getMedicalrecordByPid(p.getPid());
        PatientBean patientBean = new PatientBean();
        patientBean.setPatient(p);
        patientBean.setDept(dept);
        patientBean.setDoctor(doctor);
        patientBean.setLevel(level);
        patientBean.setMedicalrecord(medicalrecord);
        // 获取对应的检查项目列表
        List<InspectitemBean> selectedInspectList=inspectitemService.getSelectedInspectList(p.getPid());
        PatientRecordAndInspectSelectionBean allBean = new PatientRecordAndInspectSelectionBean();
        allBean.setPatientBean(patientBean);
        allBean.setSelectedInspectList(selectedInspectList);

        return Result.ok().data("code","20000").data("allBean",allBean).data("msg","查询成功");
    }


    /**
    * @Description 暂时未使用：病历首页可单独调用保存当前患者的病例书
    * @param
     * @param req
     * @param resp
    * @return com.hrbu.his.request.Result
    * @date 2021/1/8 0008 16:18
    * @author Lemo-TanYajun
    */
    public Result saveMedicalrecord(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从前台获取病例书信息json串
        String medicalrecordStr = acceptJson(req);
        if(medicalrecordStr!=null&&!"".equals(medicalrecordStr)){
            ObjectMapper objectMapper = new ObjectMapper();
            Medicalrecord medicalrecord = objectMapper.readValue(medicalrecordStr, Medicalrecord.class);
            boolean flag=medicalrecordService.saveMedicalrecord(medicalrecord);
            if(flag){
                return Result.ok().data("code","20000").data("msg","pid:"+medicalrecord.getPid()+"病例书写入成功");
            }else {
                return Result.ok().data("code","10001").data("msg","插入病例书失败为false，检查后台吧...");
            }
        }
        return Result.ok().data("code","10002").data("msg","获取前台json串为null或为空串");
    }

    /**
     * @Description 暂时未使用：病历首页可单独调用保存当前患者的病例书
     * @param
     * @param req
     * @param resp
     * @return com.hrbu.his.request.Result
     * @date 2021/1/8 0008 16:18
     * @author Lemo-TanYajun
     */
    public Result savePatientBean(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String s = acceptJson(req);
        if(s!=null&&!"".equals(s)) {
            ObjectMapper objectMapper = new ObjectMapper();
            PatientBean patientBean = objectMapper.readValue(s, PatientBean.class);

            if(patientBean==null||patientBean.getMedicalrecord()==null||patientBean.getPatient()==null){
                return Result.ok().data("code","10000").data("msg","接收患者对象或病例记录对象为null");
            }
            if(patientBean.getPatient().getPid()!=null){
                patientBean.getMedicalrecord().setPid(patientBean.getPatient().getPid());
                boolean flag = medicalrecordService.saveMedicalrecord(patientBean.getMedicalrecord());
                if(flag){
                    return Result.ok().data("code","20000").data("msg","pid:"+patientBean.getPatient().getPid()+"病例书写入成功");
                }else {
                    return Result.ok().data("code","10001").data("msg","插入病例书失败为false，检查后台吧...");
                }
            }else {
                return Result.ok().data("code","10003").data("msg","主键pid为null");
            }
        }
        return Result.ok().data("code","10002").data("msg","获取前台json串为null或为空串");
    }

    /**
     * @Description 按步骤提交病例书的最后整体向数据库提交封装数据
     * @param
     * @param req
     * @param resp
     * @return com.hrbu.his.request.Result
     * @date 2021/1/8 0008 16:18
     * @author Lemo-TanYajun
     */
    public Result saveAllRecord(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String s = acceptJson(req);
        if(s!=null&&!"".equals(s)) {
            ObjectMapper objectMapper = new ObjectMapper();
            PatientRecordAndInspectSelectionBean allBean = objectMapper.readValue(s, PatientRecordAndInspectSelectionBean.class);
            PatientBean patientBean = allBean.getPatientBean();
            List<InspectitemBean> selectedInspectList = allBean.getSelectedInspectList();
            if(allBean==null||patientBean==null||selectedInspectList==null){
                return Result.ok().data("code","10000").data("msg","前台传入就诊记录有误");
            }
            if(patientBean.getPatient().getPid()!=null){
                // 将病例书进行写入
                patientBean.getMedicalrecord().setPid(patientBean.getPatient().getPid());
                boolean flag1 = medicalrecordService.saveMedicalrecord(patientBean.getMedicalrecord());
                // 将检查项目进行写入
                boolean flag2=inspectitemService.saveInspectitemRecord(patientBean.getPatient().getPid()+"",selectedInspectList);
                if(flag1&&flag2){

                    return Result.ok().data("code","20000")
                           .data("msg","pid:"+patientBean.getPatient().getPid()+"病例书写入成功,"+
                                   selectedInspectList.size()+"条检验项目");
                }else {
                    return Result.ok().data("code","10001").data("msg","插入病例书失败为false，检查后台吧...");
                }
            }else {
                return Result.ok().data("code","10003").data("msg","主键pid为null");
            }
        }
        return Result.ok().data("code","10002").data("msg","获取前台json串为null或为空串");
    }

    public Result getInpsectItemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取检验项目列表
        List<Inspectitem> inspectitemList = inspectitemService.getInpsectItemList();
        return Result.ok().data("inspectitemList",inspectitemList);
    }

    /**
    * @Description 保存检验项目
    * @param
     * @param req
     * @param resp
    * @return com.hrbu.his.request.Result
    * @date 2021/1/8 0008 16:23
    * @author Lemo-TanYajun
    */
    public Result saveInspectitemRecord(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String pid = req.getParameter("pid");
        if(pid==null&&pid.equals("")){
            return Result.ok().data("code","40000");
        }
        String s = acceptJson(req);
        if(s!=null&&!"".equals(s)) {
            ObjectMapper objectMapper = new ObjectMapper();
            InspectitemBeanList inspectitemBeanList = objectMapper.readValue(s, InspectitemBeanList.class);
            boolean flag=inspectitemService.saveInspectitemRecord(pid,inspectitemBeanList);
            if(flag){
                return Result.ok().data("code","20000").data("msg","成功添加检验项目:"+inspectitemBeanList.getSelectedInspectList().size());
            }else {
                return Result.ok().data("code","40001").data("msg","失败：可能是未完全插入或者其他异常");
            }
        }
        return Result.ok().data("code","40002").data("msg","json获取失败或者为空串");
    }
    public Result getUnpayPatientVoList(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        /**
        * @Description 获取未交费患者列表
        * @return com.hrbu.his.request.Result
        * @date 2021/1/8 0008 16:45
        * @author Lemo-TanYajun
        */
        List<UnpayPatientVo> unpayPatientVoList=inspectitemService.getUnpayPatientVoList(0);
        if(unpayPatientVoList!=null){
            return Result.ok().data("code","20000").data("unpayPatientVoList",unpayPatientVoList);
        }
        return Result.ok().data("code","30001").data("msg","unpayPatientVoList为null");
    }
    public Result getPaiedPatientVoList(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        /**
         * @Description 获取已交费患者列表
         * @return com.hrbu.his.request.Result
         * @date 2021/1/8 0008 16:45
         * @author Lemo-TanYajun
         */
        List<UnpayPatientVo> paiedPatientVoList=inspectitemService.getUnpayPatientVoList(1);
        if(paiedPatientVoList!=null){
            return Result.ok().data("code","20000").data("paiedPatientVoList",paiedPatientVoList);
        }
        return Result.ok().data("code","30001").data("msg","paiedPatientVoList为null");
    }

    /**
    * @Description 根据pid获取未交费项目
    * @param
     * @param req
     * @param resp
    * @return com.hrbu.his.request.Result
    * @date 2021/1/6 0006 11:11
    * @author Lemo-TanYajun
    */
    public Result getUnpayVoList(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String pid = null;
        try {
            pid = req.getParameter("pid");
            if(pid==null&&pid.equals("")){
                return Result.ok().data("code","40001").data("msg","pid为空");
            }
        } catch (NullPointerException e) {
            return Result.ok().data("code","40000").data("msg","无pid参数");
        }

        List<UnpayVo> unpayVoList=inspectitemService.getUnpayVoList(pid,0);
        if(unpayVoList!=null){
            return Result.ok().data("code","20000").data("unpayVoList",unpayVoList).data("msg","获取待缴费项目成功");
        }
        return Result.ok().data("code","40002").data("msg","unpayVoList为null");
    }

    public Result getPaiedVoList(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        /**
         * @Description 获取已交费检验项目列表
         * @return com.hrbu.his.request.Result
         * @date 2021/1/8 0008 16:45
         * @author Lemo-TanYajun
         */
        String pid = null;
        try {
            pid = req.getParameter("pid");
            if(pid==null&&pid.equals("")){
                return Result.ok().data("code","40001").data("msg","pid为空");
            }
        } catch (NullPointerException e) {
            return Result.ok().data("code","40000").data("msg","无pid参数");
        }

        List<UnpayVo> paiedVoList=inspectitemService.getUnpayVoList(pid,1);
        if(paiedVoList!=null){
            return Result.ok().data("code","20000").data("paiedVoList",paiedVoList).data("msg","获取已缴费项目成功");
        }
        return Result.ok().data("code","40002").data("msg","paiedVoList为null");
    }

    public Result setPaystatus(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        /**
         * @Description 设置患者支付项目费用状态
         * @return com.hrbu.his.request.Result
         * @date 2021/1/8 0008 16:45
         * @author Lemo-TanYajun
         */
        String pid = null;
        String inspectid = null;
        try {
            pid = req.getParameter("pid");
            inspectid = req.getParameter("inspectid");
            if(pid==null&&pid.equals("")||inspectid==null&&inspectid.equals("")){
                return Result.ok().data("code","40001").data("msg","pid为空或inspectid为空");
            }
        } catch (NullPointerException e) {
            return Result.ok().data("code","40000").data("msg","无pid或inspectid参数");
        }

        boolean flag=inspectitemService.setPaystatus(pid,inspectid);
        if(flag){
            return Result.ok().data("code","20000").data("msg","缴费成功...");
        }
        return Result.ok().data("code","40002").data("msg","缴费失败...");
    }

    public Result updatePatientPstatus(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        /**
        * @Description 更新患者就诊状态
        * @return com.hrbu.his.request.Result
        * @date 2021/1/8 0008 16:52
        * @author Lemo-TanYajun
        */
        String pid = null;
        String pstatus = null;
        try {
            pid = req.getParameter("pid");
            pstatus = req.getParameter("pstatus");
            if(pid==null&&pid.equals("")||pstatus==null&&pstatus.equals("")){
                return Result.ok().data("code","40001").data("msg","pid为空或pstatus为空");
            }
        } catch (NullPointerException e) {
            return Result.ok().data("code","40000").data("msg","无pid或pstatus参数");
        }
        // 设置患者状态
        boolean flag=patientService.updatePatientPstatus(pid,pstatus);
        if(flag){
            return Result.ok().data("code","20000").data("msg","修改状态成功...");
        }
        return Result.ok().data("code","40002").data("msg","修改状态失败...");
    }
}
