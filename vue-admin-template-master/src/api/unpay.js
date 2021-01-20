import request from "@/utils/request"

export default{
    getUnpayPatientVoList(){
        return request({
            url: '/tyj/patient/getUnpayPatientVoList',
            method: 'get',
        })
    },
    getPaiedPatientVoList(){
        return request({
            url: '/tyj/patient/getPaiedPatientVoList',
            method: 'get',
        })
    },
    getUnpayVoList(pid){
        return request({
            url: `/tyj/patient/getUnpayVoList?pid=${pid}`,
            method: 'get',
        })
    },
    getPaiedVoList(pid){
        return request({
            url: `/tyj/patient/getPaiedVoList?pid=${pid}`,
            method: 'get',
        })
    },
    setPaystatus(pid,inspectid){
        return request({
            url: `/tyj/patient/setPaystatus?pid=${pid}&inspectid=${inspectid}`,
            method: 'get',
        })
    }
}