import request from '@/utils/request'

export default { 
    getPid() {
        return request({
            url: '/tyj/patient/getPid',
            method: 'get',
        })
    },
    getAllDeptList(){
        return request({
            url:'/tyj/dept/getAllDeptList',
            method:'get',
        })
    },
    getDoctorListByDeptid(deptid){
        return request({
            url:'/tyj/doctor/getDoctorList?deptid='+deptid,
            method:'get'
        })
    },
    getLevelList(){
        return request({
            url:'/tyj/level/getLevelList',
            method:'get'
        })
    },
    saveRegisteredInDB(patientPage){
        return request({
            url:'/tyj/patient/saveRegisteredInDB',
            method:'post',
            data:patientPage
        })
    },
    getPatientListPage(page,pageSize){
        return request({
            url:`/tyj/patient/getPatientListPage?page=${page}&pageSize=${pageSize}`,
            method:"get",
        })
    },
    getBackableListPage(page,pageSize){
        return request({
            url:`/tyj/patient/getBackableListPage?page=${page}&pageSize=${pageSize}`,
            method:"get",
        })
    },
    setBacknoPatient(pid){
        return request({
            url:`/tyj/patient/setBacknoPatient?pid=${pid}`,
            method:"get",
        })
    },
    getPatientListPageLikePid(pid,page,pageSize){
        return request({
            url:`/tyj/patient/getPatientListPageLikePid?pid=${pid}&page=${page}&pageSize=${pageSize}`,
            method:"get",
        })
    },
}

