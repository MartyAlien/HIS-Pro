import request from "@/utils/request"
export default{
    getSarsData(){
        return request({
            url: '/tyj/sars/getSarsData',
            method: 'get',
        })
    },
    getSarsDataBy163(){
        return request({
            url: '/tyj/sars/getSarsDataBy163',
            method: 'get',
        })
    },
    
}