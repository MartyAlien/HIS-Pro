import request from "@/utils/request"
export default{
    getUnTreatmentPatientList(){
        return request({
            url: '/tyj/patient/getUnTreatmentPatientList',
            method: 'get',
        })
    },
    getTreatedmentPatientList(){
        return request({
            url: '/tyj/patient/getTreatedmentPatientList',
            method: 'get',
        })
    },
    getCurrentAllBeanRow(pid){
        return request({
            url: `/tyj/patient/getCurrentAllBeanRow?pid=${pid}`,
            method: 'get',
        })
    },
    getPatientBean(pid){
        return request({
            url: `/tyj/patient/getPatientBean?pid=${pid}`,
            method: 'get',
        })
    },
    savePatientBean(data){
        return request({
            url: `/tyj/patient/savePatientBean`,
            method: 'post',
            data
        })
    },
    saveInspectitemRecord(list,pid){
        return request({
            url: `/tyj/patient/saveInspectitemRecord?pid=${pid}`,
            method: 'post',
            data:list
        })
    },
    saveAllRecord(patientBean,selectedInspectList){
        let list={'patientBean':patientBean,"selectedInspectList":selectedInspectList};
        return request({
            url: `/tyj/patient/saveAllRecord`,
            method: 'post',
            data:list
        })
    },
    getInpsectItemList(){
        return request({
            url: `/tyj/patient/getInpsectItemList`,
            method: 'get',
        })
    },
    updatePatientPstatus(pid,pstatus){
        if(pstatus*1==0||pstatus*1==1||pstatus*1==2){
            return request({
                url: `/tyj/patient/updatePatientPstatus?pid=${pid}&pstatus=${pstatus}`,
                method: 'get',
            })
        }else{
            return;
        }
    }
}