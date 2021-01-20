<template>
  <div class="app-container">
    <el-container>
      <el-aside width="400px">
        <el-container>
          <el-header height="360px">
            <el-card shadow="hover">
              <div slot="header">
                <el-tag type="danger" size="medium">待诊患者</el-tag>
                <el-input v-model="search" size="mini" style="width:50%;float: right; padding: 3px 0" placeholder="快速检索当页病历号"/>
              </div>
              <el-table :data="unTreatList.filter(data => !search || data.pid.toString().toLowerCase().includes(search.toLowerCase()))" style="width: 100%" height="300"
                  :row-style="{
                    cursor:'pointer'
                  }"
                  highlight-current-row
                  @row-dblclick="handleUntreatRow1"
                  @current-change="handleCurrentChange"
                >
                <el-table-column prop="pid" label="病历号" width="100px">
                </el-table-column>
                <el-table-column prop="pname" label="姓名" width="100px">
                </el-table-column>
                <el-table-column prop="createdatebystr" label="挂号日期" width="100px">
                </el-table-column>
              </el-table>
            </el-card>
          </el-header>

          <el-main style="margin-top:40px">
            <el-card shadow="hover">
              <div slot="header">
                <el-tag type="success" size="medium">已诊患者</el-tag>
                <el-input v-model="search2" size="mini" style="width:50%;float: right; padding: 3px 0" placeholder="快速检索当页病历号"/>
              </div>
              <el-table :data="treatedList.filter(data => !search2 || data.pid.toString().toLowerCase().includes(search2.toLowerCase()))" style="width: 100%" height="300"
                  :row-style="{
                    cursor:'pointer'
                  }"
                  highlight-current-row
                  @row-dblclick="handleUntreatRow2"
                  @current-change="handleCurrentChange"
                >
                <el-table-column prop="pid" label="病历号" width="100px">
                </el-table-column>
                <el-table-column prop="pname" label="姓名" width="100px">
                </el-table-column>
                <el-table-column prop="createdatebystr" label="挂号日期" width="100px">
                </el-table-column>
              </el-table>
            </el-card>
          </el-main>
        </el-container>
      </el-aside>

      <el-card shadow="hover" style="width:100%">
      <el-main  width="100%" >
          <el-tabs v-model="pane">
            <el-tab-pane label="病历首页" :disabled="paneIsDisabled[0]&&mode==0" name="1">
              <el-form
                :model="patientBean"
                :rules="rules"
                ref="patientBean"
                label-width="100px"
                class="demo-ruleForm"
              >
                <el-row :gutter="10">
                  <el-col :span="6">
                    <el-form-item label="患者姓名" prop="patient.pname">
                      <el-input v-model="patientBean.patient.pname" disabled ></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="患者年龄" prop="patient.age">
                      <el-input v-model="patientBean.patient.age" disabled ></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="看诊科室" prop="dept.deptname">
                      <el-input v-model="patientBean.dept.deptname" disabled ></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="看诊医师" prop="doctor.dname">
                      <el-input v-model="patientBean.doctor.dname" disabled ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>
                <el-form-item label="病历描述" prop="medicalrecord.description">
                  <el-input type="textarea" rows="5"  v-model="patientBean.medicalrecord.description" :disabled="mode==1"></el-input>
                </el-form-item>
                <el-form-item label="既往病史" prop="medicalrecord.medicalhistory">
                  <el-input type="textarea" rows="5"  v-model="patientBean.medicalrecord.medicalhistory" :disabled="mode==1"></el-input>
                </el-form-item>
                <el-form-item label="家族病史" prop="medicalrecord.familyhistory">
                  <el-input type="textarea" rows="5"  v-model="patientBean.medicalrecord.familyhistory" :disabled="mode==1"></el-input>
                </el-form-item>
                <el-form-item label="初步结果" prop="medicalrecord.initialresult">
                  <el-input type="textarea" rows="5"  v-model="patientBean.medicalrecord.initialresult" :disabled="mode==1"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button 
                  @click="nextPane(1)" 
                  v-show="mode==0" 
                  :disabled="patientBean.patient.pid==null||patientBean.patient.pid*1==0"
                  type="danger">下一步</el-button>
                  <el-button @click="resetForm('patientBean')" v-show="mode==0">重置</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>


            <el-tab-pane label="检验管理" :disabled="paneIsDisabled[1]&&mode==0" name="2">
              <el-form
                :model="patientBean"
                :rules="rules"
                ref="patientBean"
                label-width="100px"
                class="demo-ruleForm"
              >
                <el-row :gutter="10">
                  <el-col :span="6">
                    <el-form-item label="患者姓名" prop="patient.pname">
                      <el-input v-model="patientBean.patient.pname" disabled ></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="患者年龄" prop="patient.age">
                      <el-input v-model="patientBean.patient.age" disabled ></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="看诊科室" prop="dept.deptname">
                      <el-input v-model="patientBean.dept.deptname" disabled ></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="6">
                    <el-form-item label="看诊医师" prop="doctor.dname">
                      <el-input v-model="patientBean.doctor.dname" disabled ></el-input>
                    </el-form-item>
                  </el-col>
                </el-row>

                <el-form-item label="检验项目" prop="pname">
                   <el-card shadow="hover" >
                      <div slot="header" class="clearfix">
                        <el-button type="danger" round icon="el-icon-s-release" style="float: right;margin-left:10px" 
                        @click="deleteSelections"
                        :disabled="mode==1">移除选中</el-button>
                        <el-button type="primary" round icon="el-icon-plus" style="float: right;" 
                        @click="openDialog"
                        :disabled="mode==1">新增项目</el-button>
                      </div>
                      <el-table
                        :data="selectedInspectList"
                        style="width: 100%"
                        @selection-change="getSelections"
                        max-height="400">
                        <el-table-column type="selection" width="50px"></el-table-column>
                        <el-table-column
                          type="index"
                          width="100"
                          label="序号">
                        </el-table-column>
                        <el-table-column
                          prop="inspectname"
                          label="检查项目">
                        </el-table-column>
                        <el-table-column
                          prop="price"
                          label="检查费用">
                          <template slot-scope="scope">
                            <el-tag
                              type='success'
                              disable-transitions>{{scope.row.price|showPrice}}</el-tag>
                          </template>
                      </el-table-column>
                      <el-table-column
                          label="数量"
                          width="200px">
                          <template slot-scope="scope">
                            <el-button
                              @click.native.prevent="handleRow(scope.row);scope.row.amount++;"
                              icon="el-icon-circle-plus-outline"
                              type="danger"
                              size="mini"
                              circle
                              :disabled="scope.row.amount>9||mode==1">
                            </el-button>   
                            <el-tag size="medium" type="warning" effect="plain" style="margin:0px 5px">
                              {{scope.row.amount}}
                            </el-tag>   
                            <el-button
                              @click.native.prevent="handleRow(scope.row);scope.row.amount--;"
                              icon="el-icon-remove-outline"
                              type="primary"
                              size="mini"
                              circle
                              :disabled="scope.row.amount==1||mode==1">
                            </el-button>
                          </template>
                        </el-table-column>
                        <el-table-column
                          fixed="right"
                          label="操作"
                          width="120">
                          <template slot-scope="scope">
                            <el-button
                              @click.native.prevent="deleteRow(scope.$index, selectedInspectList)"
                              type="text"
                              :disabled="mode==1">
                              <el-tag effect="dark" :type="mode==0?'danger':'info'">
                                <i class="el-icon-delete"></i>
                                移除
                              </el-tag>
                            </el-button>
                          </template>
                        </el-table-column>
                        <el-table-column prop='paystatus' label="支付状态" v-if="mode==1"
                        :filter-method="filterTag"
                        :filters="[{ text: '已缴费', value: 1 }, { text: '未缴费', value: 0 }]"
                        filter-placement="bottom-end" >
                          <!--filter-method是一个筛选方法，他根据设置的filter的value进行筛选
                              filters是一个数组，里面的text是页面能够看到的筛选条件名称，value是用筛选的值-->
                          <template slot-scope="scope">
                            <!--根据pstatus更改显示内容-->
                            <el-tag
                              :type='scope.row.paystatus*1===0?"warning":"success"'
                              disable-transitions>{{scope.row.paystatus | showPstatus}}</el-tag>
                          </template>

                        </el-table-column>
                      </el-table>
                      <el-tag style="box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);font-size:20px"
                      type="danger"
                      size="large">

                      总共:{{getTotalPrice | showPrice}}
                      </el-tag>
                   </el-card>
                </el-form-item>
                <el-form-item>
                  <!-- <el-button type="primary" @click="submitForm('ruleForm')"
                  :disabled="selectedInspectList.length<1"
                    >开立项目</el-button
                  > -->
                  <el-button @click="prePane(1)" v-show="mode==0" type="info">上一步</el-button>
                  <el-button @click="nextPane(2)" v-show="mode==0" :disabled="patientBean.patient.pid==null||patientBean.patient.pid*1==0" 
                  type="danger">下一步</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
            <!--******************************分**割**线***************************************-->
            <el-dialog title="新增检验项目" :visible.sync="dialogTableVisible" :close-on-click-modal="false">
              <el-card shadow="hover">{{search3}}
                <el-table :data="inspectAllList"
                  max-height="200px"
                  @selection-change="onSelection"
                  @select="onSelectOne"
                   ref="multipleTable" >
                  <el-table-column type="selection" width="50px"></el-table-column>
                  <el-table-column property="id" label="编号" width="50px"></el-table-column>
                  <el-table-column property="inspectname" label="检验项目名称"> </el-table-column>
                  <el-table-column
                      prop="price"
                      label="检查费用">
                      <template slot-scope="scope">
                        <el-tag
                          type='success'
                          disable-transitions>{{scope.row.price|showPrice}}</el-tag>
                      </template>
                  </el-table-column>
                </el-table>
              </el-card>
              <el-row style="margin-top:10px;border:dashed 2px #56aa55;padding:10px">
                <el-col :span="2"><span>预选项目</span></el-col>
                <el-col :span="16" style="background-color:rgba(91,126,145,0.5);padding:10px;border-radius:10px" :style="tempSelectList.length==0?{height:'62px'}:''">
                    <el-tag
                        v-for="tag in tempSelectList"
                        :key="tag.index"
                        closable
                        :type="tagTypes[parseInt(Math.random()*tagTypes.length)]"
                        @close="toggleSelection(tag)"
                        style="margin:5px;box-shadow:0px 0px 4px 2px #7b7c7d"
                        effect="dark">
                        {{tag.inspectname}}
                      </el-tag>
                </el-col>
              </el-row>
              <span slot="footer" class="dialog-footer">
                <el-button @click="dialogTableVisible = false">取 消</el-button>
                <el-button type="primary" @click="confirmList">确 定</el-button>
              </span>
            </el-dialog>
            <!--******************************分**割**线***************************************-->
            <el-tab-pane label="检查管理" :disabled="paneIsDisabled[2]&&mode==0" name="3">检查管理
              <el-button @click="prePane(2)" v-show="mode==0" type="info">上一步</el-button>
              <el-button @click="nextPane(3)" v-show="mode==0" :disabled="patientBean.patient.pid==null||patientBean.patient.pid*1==0"
              type="danger">下一步</el-button>
            </el-tab-pane>
            <el-tab-pane label="最终确诊" :disabled="paneIsDisabled[3]&&mode==0" name="4">最终确诊
              <el-form
                :model="patientBean"
                :rules="rules"
                ref="patientBean"
                label-width="100px"
                class="demo-ruleForm"
              >
                  <el-row :gutter="10">
                    <el-col :span="6">
                      <el-form-item label="患者姓名" prop="patient.pname">
                        <el-input v-model="patientBean.patient.pname" disabled ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="患者年龄" prop="patient.age">
                        <el-input v-model="patientBean.patient.age" disabled ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="看诊科室" prop="dept.deptname">
                        <el-input v-model="patientBean.dept.deptname" disabled ></el-input>
                      </el-form-item>
                    </el-col>
                    <el-col :span="6">
                      <el-form-item label="看诊医师" prop="doctor.dname">
                        <el-input v-model="patientBean.doctor.dname" disabled ></el-input>
                      </el-form-item>
                    </el-col>
                  </el-row>
                  <el-form-item label="结果" prop="medicalrecord.result">
                    <el-input type="textarea" rows="5"  v-model="patientBean.medicalrecord.result" :disabled="mode==1"></el-input>
                  </el-form-item>
                  <el-form-item label="最终确诊" prop="medicalrecord.finalresult">
                    <el-input type="textarea" rows="5"  v-model="patientBean.medicalrecord.finalresult" :disabled="mode==1"></el-input>
                  </el-form-item>

                  <el-button @click="prePane(3)" v-show="mode==0" type="info">上一步</el-button>
                  <el-button type="primary" @click="submitAllForm()" 
                    :disabled="patientBean.patient.pid==null||patientBean.patient.pid*1==0"
                    v-show="mode==0"
                  >立即提交</el-button>

                </el-form>


            </el-tab-pane>
          </el-tabs>
      </el-main>
      </el-card>
    </el-container>
  </div>
</template>

<script>
import dPatient from "@/api/dpatient"
export default {
  data() {
    return {
      rules: {
        "patient.pname":[{required: true, message: "必须先获取到信息", trigger: "blur" }],
        "patient.age":[{required: true, message: "必须先获取到信息", trigger: "blur" }],
        "dept.deptname":[{required: true, message: "必须先获取到信息", trigger: "blur" }],
        "doctor.dname":[{required: true, message: "必须先获取到信息", trigger: "blur" }],
        "medicalrecord.description": [{ required: true, message: "请填写病历描述", trigger: "blur" }],
        "medicalrecord.medicalhistory": [{ required: true, message: "请填写既往病史", trigger: "blur" }],
        "medicalrecord.familyhistory": [{ required: true, message: "请填写家族病史", trigger: "blur" }],
        "medicalrecord.initialresult": [{ required: true, message: "请填写初步结果", trigger: "blur" }],
        "medicalrecord.result": [{ required: true, message: "请填写结果", trigger: "blur" }],
        "medicalrecord.finalresult": [{ required: true, message: "请填写最终确诊", trigger: "blur" }]
      },
      paneIsDisabled:[false,true,true,true],
      pane:"1",
      search:'',
      search2:'',
      search3:"",
      unTreatList:[],
      treatedList:[],
      selectedInspectList:[],
      dialogTableVisible:false,
      tempSelectList:[],
      tagTypes:["success","info","danger","warning",""],
      inspectAllList: [],
      outSelections:[],
      mode:0,
      patientBean:{
        patient:{pname:"",age:""},
        dept:{deptname:""},
        doctor:{dname:""},
        level:{},
        medicalrecord:{
          description: "",
          medicalhistory:"",
          familyhistory: "",
          initialresult: "",
          result:"",
          finalresult:""
        },
      },
      currentRow: null,
    };
  },
  methods: {
      submitAllForm() {
        console.log(this.patientBean);
        if (this.isRight(3)) {
          dPatient.saveAllRecord(this.patientBean,this.selectedInspectList).then(resp=>{
            this.$message({
              message: resp.data.msg,
              type: resp.data.code==='20000'?'success':'error',
              center:true
            });
            // 如果code=20000 更新数据：待诊患者列表  已诊患者列表
            if(resp.data.code==='20000'){
              dPatient.updatePatientPstatus(this.patientBean.patient.pid,0).then(resp=>{
                if(resp.data.code==="20000"){
                  // 更新待诊、已诊数据
                  this.getUnTreatmentPatientList();
                  this.getTreatedmentPatientList();
                  this.mode=0;
                  this.getInpsectItemList();
                  this.prePane(1);
                  this.resetForm('patientBean');
                }
              });
            }
          })

        } else {
          this.$message({
            message: '提交失败，数据未完善',
            type: 'error',
            center:true
          });
          return false;
        }
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
        this.patientBean.patient.pid="";
        this.patientBean.medicalrecord.description="";
        this.patientBean.medicalrecord.medicalhistory="";
        this.patientBean.medicalrecord.familyhistory="";
        this.patientBean.medicalrecord.initialresult="";
        this.patientBean.medicalrecord.result="";
        this.patientBean.medicalrecord.finalresult="";
        this.currentRow=null;
      },
      getUnTreatmentPatientList(){
        dPatient.getUnTreatmentPatientList().then(resp=>{
          console.log(resp);
          if(resp.data.code==="20000"){
            this.unTreatList=resp.data.unTreatmentPatientList;
          }else if(resp.data.code==="10002"){
            this.$message({
              message: '暂无待诊患者',
              type: 'info',
              center:true
            });
            this.unTreatList=[];
          }else if(resp.data.code==="10001"){
            this.$message({
              message: '查询待诊患者信息为null',
              type: 'error',
              center:true
            });
          }
        })
      },
      getTreatedmentPatientList(){
        dPatient.getTreatedmentPatientList().then(resp=>{
          console.log(resp);
          if(resp.data.code==="20000"){
            this.treatedList=resp.data.treatedmentPatientList;
          }else if(resp.data.code==="10002"){
            this.$message({
              message: '查询已诊患者数据小于1',
              type: 'info',
              center:true
            });
          }else if(resp.data.code==="10001"){
            this.$message({
              message: '查询已诊患者信息为null',
              type: 'error',
              center:true
            });
          }
        })
      },
      handleUntreatRow1(row, column, event){
        this.getPatientBeanRow(row,column,event);
        this.mode=0;
        this.getInpsectItemList();
        this.prePane(1);
      },
      handleUntreatRow2(row, column, event){
        this.getCurrentAllBeanRow(row,column,event);
        this.mode=1;
        this.prePane(1);
      },
      getCurrentAllBeanRow(row,column,event){
        dPatient.getCurrentAllBeanRow(row.pid).then(resp=>{
          this.$message({
            message: resp.data.msg,
            type: resp.data.code==='20000'?'success':'error',
            center:true
          });
          if(resp.data.code==="20000"){
            this.patientBean=resp.data.allBean.patientBean;
            this.selectedInspectList=resp.data.allBean.selectedInspectList;
          }
        })
      },
      getPatientBeanRow(row, column, event){
        console.log(row);
        //todo 重置完还未完全清除记录
        this.resetForm("patientBean");
        dPatient.getPatientBean(row.pid).then(resp=>{
          if(resp.data.code=="20000"){
            this.$message({
              message: '获取成功: 请仔细校验',
              type: 'success',
              center:true
            });
            if(resp.data.patientBean.medicalrecord!=null){
              this.patientBean=resp.data.patientBean;
            }else{
              this.patientBean.patient=resp.data.patientBean.patient;
              this.patientBean.doctor=resp.data.patientBean.doctor;
              this.patientBean.dept=resp.data.patientBean.dept;
              this.patientBean.level=resp.data.patientBean.level;
            }
            console.log(this.patientBean);
          }else if(resp.data.code=="10000"){
            this.$message({
              message: '未获取到pid',
              type: 'error',
              center:true
            });
          }else if(resp.data.code=="10001"){
            this.$message({
              message: '根据pid未能成功获取对应患者数据 ',
              type: 'error',
              center:true
            });
          }else if(resp.data.code=="10002"){
            this.$message({
              message: '根据pid获取到的数据为0',
              type: 'warning',
              center:true
            });
          }
        })
      },
      handleCurrentChange(val) {
        this.currentRow = val;
      },
      deleteRow(index, rows) {
        rows[index].amount=1;
        this.inspectAllList=this.inspectAllList.map(item=>{
          if(item.id===rows[index].id){
            item.amount=1;
          }
          return item;
        })
        rows.splice(index, 1);
      },
      openDialog(){
        if(this.patientBean.patient.pid+""==="undefined"||(this.patientBean.patient.pid+"").length<1){
            this.$alert('<i class="el-icon-loading"></i><strong style="color:#ee7948;">请先选中待诊患者</strong>', '警告(warning)', {
              dangerouslyUseHTMLString: true
            });
        }else{
          if(this.selectedInspectList.length>0){
            this.$refs.multipleTable.clearSelection(); 
            this.tempSelectList=this.selectedInspectList;
            this.tempSelectList.forEach(row => {
              this.$refs.multipleTable.toggleRowSelection(row);
            });
          }
          this.dialogTableVisible=true;
        }
      },
      getSelections(selection, row){
        this.outSelections=selection;
      },
      deleteSelections(){
        let arr=this.selectedInspectList.filter(e=>{
          let flag=true;
          for (const x of this.outSelections) {
            if(x===e){
              flag=false;
              if(x.amount>1){
                this.inspectAllList=this.inspectAllList.map(item=>{
                  if(item.id===x.id){
                    item.amount=1;
                  }
                  return item;
                })
              }
              break;
            }
          }
          return flag;
        })
        this.selectedInspectList=arr;
      },
      onSelection(selection, row){
        this.tempSelectList=selection;
      },
      onSelectOne(selection, row){
        if(selection.length<this.selectedInspectList.length){
          // 如果用户在已经选中多个并且也设置多个数量的情况下，取消了某一项目的勾选，应该将其数量置为1
          this.tempSelectList.splice(this.tempSelectList.indexOf(row),1);
          row.amount=1;
        }
      },
      toggleSelection(row){
        // 用户直接关闭了预选标签，也应该将对应的数据项的amount置为1
        this.$refs.multipleTable.toggleRowSelection(row);
        for(var i=0;i<this.inspectAllList.length;i++){
          if(this.inspectAllList[i].id===row.id){
            this.inspectAllList[i].amount=1;
          }
        }
      },
      getInpsectItemList(){
        dPatient.getInpsectItemList().then(resp=>{
          this.inspectAllList=resp.data.inspectitemList.map(item=>{
            return Object.assign({}, item, { amount: 1});
          });
        })
      },
      handleRow(row){
        console.log(row);
      },
      confirmList(){
        this.selectedInspectList=this.tempSelectList;
        this.dialogTableVisible = false;
        console.log(this.selectedInspectList);
      },
      nextPane(index){
        if(index==1){
          if (this.isRight(index)) {
              this.pane=index+1+'';
          }else{
            this.$message({
              message: '请完善当页信息后才能进行下一步',
              type: 'error',
              center:true
            });
          }
        }else{
              this.pane=index+1+'';
        }
        this.paneIsDisabled=[true,true,true,true];
      },
      prePane(index){
        this.pane=index+'';
        this.paneIsDisabled=[true,true,true,true];
      },
      isRight(index){
        let flag= this.isNullStr(this.patientBean.medicalrecord.description)&&
        this.isNullStr(this.patientBean.medicalrecord.familyhistory)&&
        this.isNullStr(this.patientBean.medicalrecord.medicalhistory)&&
        this.isNullStr(this.patientBean.medicalrecord.initialresult);
        if(index!=3){
          return flag;
        }else{
          return flag&&this.isNullStr(this.patientBean.medicalrecord.finalresult)&&
          this.isNullStr(this.patientBean.medicalrecord.result);
        }
      },
      isNullStr(str){
        if(str===null||str===undefined||(str+'').trim().length<=0){
          return false;
        }else{
          return true;
        }
      },
      filterTag(value, row) {
        return row.paystatus === value;
      }
    },
    created(){
      this.getUnTreatmentPatientList();
      this.getTreatedmentPatientList();
    },
    filters:{
        // 过滤器
        showPrice(value){
            return '￥'+(value>9?'':'0')+value.toFixed(2)
        },
        showPstatus(value){
          switch(value*1){
            case 0:
              return "未缴费";
              break;
            case 1:
              return "已缴费";
              break;
          }
        }
    }, 
    computed: {
      // 模糊搜索右侧子表名称筛选
      tableDialog() {
        return this.inspectAllList.filter(data => !this.search3 || data.inspectname.toString().toLowerCase().includes(this.search3.toLowerCase()));
      },
      getTotalPrice(){
          return this.selectedInspectList.reduce((total,b)=>{
              return total+b.price*b.amount
          },0)
      }
    },
    watch:{
      'patientBean.patient':{
        handler:function(val,oldval){
          if(this.patientBean.patient.pstatus==="1"){
            this.selectedInspectList=[];
          }
          this.tempSelectList=[];
          this.outSelections=[];
        },
        deep:true,
        //immediate:true
      }
    }
};
</script>

<style scoped>
#aside {
  border: 5px solid #dcdfe6;
  border-radius: 10px;
  padding: 12px;
}
</style>