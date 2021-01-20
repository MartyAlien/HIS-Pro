<template>
  <div class="app-container">
      <el-container>
        <el-aside width="30%" style="margin-top:20px;">
            <el-card shadow="hover" style="border-radius:10px;backgroundColor:rgb(108,140,131);">
                <div slot="header" class="clearfix">
                    <strong style="font-size:20px">待缴费患者列表</strong>
                </div>
                <div align="center">
                    <el-scrollbar style="height: 200px;" :native="false"  :noresize="false" tag="section">
                        <span v-show="unpayPatientVoList.length<=0">暂无待缴费患者</span>
                        <span v-for="item in unpayPatientVoList" :key="item.pid" >
                            <el-popconfirm
                            confirm-button-text='选中并查看'
                            cancel-button-text='预览待缴费信息'
                            icon="el-icon-info"
                            icon-color="#f09199"
                            :title="'病历号:'+item.pid+'  姓名:'+item.pname"
                            @onConfirm="selectAndPay(item,mode=0);currentName=item.pname"
                            @onCancel="previewInfo(item,0)"
                            >
                            <el-button slot="reference" 
                            :type="btnType[Math.floor(Math.random()*btnType.length)]" round
                            style="margin:5px">
                                {{item.pid}}--{{item.pname}}
                            </el-button>
                            </el-popconfirm>
                        </span>
                    </el-scrollbar>
                </div>
            </el-card>

            <el-card shadow="hover" style="border-radius:10px;backgroundColor:#cd5e3c;margin-top:30px">
                <div slot="header" class="clearfix">
                    <strong style="font-size:20px">部分检验项目已缴费列表</strong>
                </div>
                <div align="center">
                    <el-scrollbar style="height: 200px;" :native="false"  :noresize="false" tag="section">
                        <span v-show="paiedPatientVoList.length<=0">暂无已缴费项目的患者信息</span>
                        <span v-for="item in paiedPatientVoList" :key="item.pid" >

                            <el-popconfirm
                            confirm-button-text='选中并查看'
                            cancel-button-text='预览待缴费信息'
                            icon="el-icon-info"
                            icon-color="#3b7960"
                            :title="'病历号:'+item.pid+'  姓名:'+item.pname"
                            @onConfirm="selectAndPay(item,mode=1);currentName=item.pname"
                            @onCancel="previewInfo(item,1)"
                            >
                            <el-button slot="reference" 
                            :type="btnType[Math.floor(Math.random()*btnType.length)]" round
                            style="margin:5px">
                                {{item.pid}}--{{item.pname}}
                            </el-button>
                            </el-popconfirm>
                        </span>
                    </el-scrollbar>
                </div>
            </el-card>
        </el-aside>
        <el-main>
            <el-card shadow="hover" style="border-radius:10px;backgroundColor:#69b076;">
                <div slot="header" class="clearfix">
                    <strong style="font-size:20px;color:#426579">{{currentName===""?"":currentName+": "}}{{mode==0?'待缴费检验项目':'已缴费检验项目'}}</strong>
                </div>
                <el-card style="backgroundColor:rgba(255,255,255,.7);" shadow="hover" v-show="unpayVoList.length>0||paiedPatientVoList.length>0||unpayPatientVoList.length>0">
                    <el-table
                    :header-cell-style="{color:'#2f5d50',fontSize:'16px','font-family':'幼圆'}"
                    ref="multipleTable"
                    height="430"
                    :data="mode==0?unpayVoList:paiedVoList"
                    tooltip-effect="dark"
                    style="width: 100%"
                    @selection-change="handleSelectionChange">
                        <el-table-column type="selection" width="55"></el-table-column>
                        <el-table-column prop="pid" label="病例号"> </el-table-column>
                        <el-table-column prop="pname" label="患者姓名"> </el-table-column>
                        <el-table-column prop="inspectid" label="项目编号">  </el-table-column>
                        <el-table-column prop="inspectname" label="项目名称"> </el-table-column>
                        <el-table-column prop="price" label="单次费用"> 
                            <template slot-scope="scope">
                                <el-tag
                                    type="'primary"
                                    disable-transitions>{{scope.row.price|showPrice}}</el-tag>
                            </template>
                        </el-table-column>
                        <el-table-column prop="amount" label="数量"> </el-table-column>
                        <el-table-column label="单项缴费" > 
                            <template slot-scope="scope" >
                                <el-button type="primary" size="small" @click="toPays(scope.row,0)" :disabled="mode==1">{{mode==0?'缴 费':'已缴费'}}</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                    <el-row style="margin-top:20px">
                        <el-col :span="6"><el-tag effect="dark" type="success" size="large">总计费用：{{mode==0?totalMoney1:totalMoney2 | showPrice}}</el-tag></el-col>
                        <el-col :offset="14" :span="4" v-show="mode==0"><el-button type="primary" @click="toPays(selectionUnpay,1)" :disabled="mode==1">缴纳选中项目费用</el-button></el-col>
                    </el-row>
                </el-card>
                <el-card shadow="hover" v-show="unpayVoList.length<=0&&paiedPatientVoList.length<=0&&unpayPatientVoList.length<=0" style="text-align:center;background-color:rgba(255,255,255,0.5);">
                    <img src="@/assets/commonImgs/p02.png" width="487" height="50%" class="image">
                </el-card>
            </el-card>
        </el-main>

    </el-container>
    <!--***************************分********割********线***************************-->
    <el-dialog title="信息预览" :visible.sync="dialogInfoVisible" width="30%">
        <el-card shadow="hover" style="font-size:18px">
            <el-row>
            <el-col :span="12">
                <el-row class="row" style="margin-top:8px">
                    <el-col :span="12"><i class="el-icon-info"></i><label>病历号</label></el-col>
                    <el-col :span="12">{{previewInfoPatient.pid}}</el-col>
                </el-row>
                <el-row class="row">
                    <el-col :span="12"><i class="el-icon-user"></i><label >姓名</label></el-col>
                    <el-col :span="12">{{previewInfoPatient.pname}}</el-col>
                </el-row>
                <el-row class="row">
                    <el-col :span="12"><i class="el-icon-s-custom"></i><label>主治医师</label></el-col>
                    <el-col :span="12">{{previewInfoPatient.dname}}</el-col>
                </el-row>
                <el-row class="row">
                    <el-col :span="12"><i class="el-icon-s-shop"></i><label>就诊科室</label></el-col>
                    <el-col :span="12">{{previewInfoPatient.deptname}}</el-col>
                </el-row>
            </el-col>
            <el-col :span="10" :offset="2">
                <img src="@/assets/commonImgs/p01.png" width="140px" height="140px">
            </el-col>
        </el-row>
        </el-card>
        <span slot="footer" class="dialog-footer">
            <el-button type="text" @click="dialogInfoVisible = false;getList(previewInfoPatient.pid,xmode);currentName=previewInfoPatient.pname">{{xmode==0?'选中去缴费':'查看缴费项'}}</el-button>
            <el-button type="primary" @click="dialogInfoVisible = false">关 闭</el-button>
        </span>
    </el-dialog>
  </div>
</template>


<script>
import Patient from "@/api/patient"
import Unpay from "@/api/unpay"
  export default {
    data() {
      return {
        btnType:['primary','success','danger','warning',''],
        unpayPatientVoList:[],
        paiedPatientVoList:[],
        dialogInfoVisible:false,
        previewInfoPatient:{},
        unpayVoList:[],
        paiedVoList:[],
        selectionUnpay:[],
        mode:0,
        xmode:0,
        totalPay:0,
        currentName:''
      }
    },
    methods: {
        fetchUnpayPatientVoListData(){
            Unpay.getUnpayPatientVoList().then(resp=>{
                if(resp.data.code==="20000"){
                    this.unpayPatientVoList=resp.data.unpayPatientVoList;
                }else if(resp.data.code==="30001"){
                    this.$message({
                        message: resp.data.msg,
                        type: 'error',
                        center:true
                    });
                }
            })
        },
        fetchPaiedPatientVoListData(){
            Unpay.getPaiedPatientVoList().then(resp=>{
                if(resp.data.code==="20000"){
                    this.paiedPatientVoList=resp.data.paiedPatientVoList;
                }else if(resp.data.code==="30001"){
                    this.$message({
                        message: resp.data.msg,
                        type: 'error',
                        center:true
                    });
                }
            })
        },
        selectAndPay(obj,xmode){
            if(xmode==0){
                this.getUnpayList(obj.pid);
            }else if(xmode==1){
                this.getPaiedList(obj.pid);
            }
        },
        previewInfo(obj,xmode){
            this.dialogInfoVisible=true;
            this.xmode=xmode;
            if(obj!==undefined){
                this.previewInfoPatient=obj;
            }
        },
        getList(pid,xmode){
            if(xmode==0){
                this.getUnpayList(pid);
                this.mode=0;
            }else if(xmode==1){
                this.getPaiedList(pid);
                this.mode=1;
            }
        },
        getUnpayList(pid,flag=true){
            if(pid===""||pid===undefined){
                this.$message({
                    message: "pid获取失败",
                    type: 'error',
                    center:true
                });
            }else{
                Unpay.getUnpayVoList(pid).then(resp=>{
                    if(resp.data.code==="20000"){
                        if(flag){
                            this.$message({
                                message: resp.data.msg,
                                type: 'success',
                                center:true
                            });
                        }
                        this.unpayVoList=resp.data.unpayVoList;
                        let payMoney=0;
                        if(this.unpayVoList.length>0){
                            for (const e of this.unpayVoList) {
                                payMoney+=e.totalPay;
                            }
                        }
                        this.totalPay=payMoney;
                    }else{
                        this.$message({
                            message: resp.data.msg,
                            type: 'error',
                            center:true
                        });
                    }
                })
            }
        },
        getPaiedList(pid,flag=true){
            if(pid===""||pid===undefined){
                this.$message({
                    message: "pid获取失败",
                    type: 'error',
                    center:true
                });
            }else{
                Unpay.getPaiedVoList(pid).then(resp=>{
                    if(resp.data.code==="20000"){
                        if(flag){
                            this.$message({
                                message: resp.data.msg,
                                type: 'success',
                                center:true
                            });
                        }
                        this.paiedVoList=resp.data.paiedVoList;
                        if(this.paiedVoList.length>0){
                            this.totalPay=0;
                            for (const e of this.paiedVoList) {
                                this.totalPay+=e.totalPay;
                            }
                        }else{
                            this.totalPay=0
                        }
                    }else{
                        this.$message({
                            message: resp.data.msg,
                            type: 'error',
                            center:true
                        });
                    }
                })
            }
        },
        handleSelectionChange(selection){
            this.selectionUnpay=selection;
        },
        toPays(unpayList,mode){
            //mode=0 是单行的对象
            //mode=1 是选中的集合
            let inspectidStr='';
            let pid=''
            switch(mode){
                case 0:
                    if(unpayList!=null){
                        inspectidStr=unpayList.inspectid;
                        pid=unpayList.pid;
                    }
                    break;
                case 1:
                    if(unpayList!=null&&unpayList.length>0){
                        for (const e of unpayList) {
                            inspectidStr+=e.inspectid+',';
                            pid=e.pid;
                        }
                        inspectidStr=inspectidStr.substring(0,inspectidStr.length-1);
                    }
                    break;
                default:
                    console.log('没有此模式');
                    break;
            }
            if(inspectidStr!==''&&pid!==''){
                Unpay.setPaystatus(pid,inspectidStr).then(resp=>{
                    this.$message({
                        message: resp.data.msg,
                        type: resp.data.code==="20000"?'success':'error',
                        center:true
                    });
                    // 更新数据
                    this.fetchUnpayPatientVoListData();
                    this.fetchPaiedPatientVoListData();
                    // 填写false的话可以在成功获取数据后不提示消息
                    this.getUnpayList(pid,false);
                    this.getPaiedList(pid,false);
                })
            }else{
                this.$message({
                    message: "没有成功获取到选中项的pid和inspectid",
                    type: 'error',
                    center:true
                });
            }
        }
    },
    created(){
        this.fetchUnpayPatientVoListData();
        this.fetchPaiedPatientVoListData();
        setTimeout(()=>{
            if(this.unpayPatientVoList.length>0&&this.unpayPatientVoList!=undefined){
                this.getList(this.unpayPatientVoList[0].pid,0);
                this.currentName=this.unpayPatientVoList[0].pname;
            }
        },500)
    },
    filters:{
        // 过滤器
        showPrice(value){
            return '￥'+(value>9?'':'0')+value.toFixed(2)
        },
    },
    computed:{
        getTotalPrice(){
            return this.patient.money+this.patient.delivery*1;
        }
    },
    watch:{
        'unpayVoList':{
            handler:function(val,oldval){
                if(val.length==0){
                    this.$message(this.currentName+'已缴清检查费用，2s后将自动切换');
                    setTimeout(()=>{
                        if(this.unpayPatientVoList.length>0){
                            this.getList(this.unpayPatientVoList[0].pid,0);
                            this.currentName=this.unpayPatientVoList[0].pname;
                        }else{
                            this.currentName="";
                            if(this.paiedPatientVoList.length>0){
                                this.getList(this.paiedPatientVoList[0].pid,1);
                                this.currentName=this.paiedPatientVoList[0].pname;
                            }
                        }
                    },2000);
                }
            },
            deep:true,
        },
        'unpayPatientVoList':{
            handler:function(val,oldval){
                if(val.length==0){
                    setTimeout(()=>{
                        this.$notify({
                            title: '提示',
                            message: '检测到无待缴费检验项目即将跳转',
                            duration: 3000
                        });
                        this.currentName="";
                        setTimeout(()=>{
                            if(this.paiedPatientVoList.length>0){
                                this.getList(this.paiedPatientVoList[0].pid,1);
                                this.currentName=this.paiedPatientVoList[0].pname;
                            }
                        },2000);
                        
                    },1000);
                }
            },
            deep:true,
        }
    },
    computed:{
        totalMoney1(){
            let total=0;
            if(this.unpayVoList.length>0){
                this.unpayVoList.forEach(element => {
                    total+=element.totalPay;
                });
            }
            return total;
        },
        totalMoney2(){
            let total=0;
            if(this.paiedVoList.length>0){
                this.paiedVoList.forEach(element => {
                    total+=element.totalPay;
                });
            }
            return total;
        },
    }
  }
</script>
<style>
    i{
        color:#5b7e91;
        margin-right:5px
    }
    .row{
        margin-bottom: 10px;
    }

    .el-scrollbar {
        height: 100%;
    }
    .el-scrollbar__wrap {
        overflow-x: hidden;
    }
    .el-scrollbar__thumb{
     background: #f0e7e7;
   }
    
 .el-table, .el-table__expanded-cell {
background-color: transparent;
}
.el-table tr,.el-table th {
background-color: transparent !important;
}
</style>