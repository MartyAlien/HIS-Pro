<template>
  <div class="app-container">
    <el-divider content-position="left" @click="isshow=!isshow">
      <el-tooltip class="item" effect="dark" content="可收起下方模块" placement="bottom-start" :disabled="!isshow">
        <el-button>录入挂号信息</el-button>
      </el-tooltip>
    </el-divider>
    <el-collapse-transition>
      <div v-show="isshow">
        <el-form :model="patient" :rules="rules" ref="patient" label-width="100px" class="demo-patient">
          <el-row :gutter="10">
            <el-col :span=6>
              <el-form-item label="病历号" prop="pid">
                <el-input v-model="patient.pid" disabled></el-input>
              </el-form-item>
            </el-col>
            <el-col :span=6>
              <el-form-item label="姓名" prop="pname">
                <el-input v-model="patient.pname" placeholder="必填: 规定为2-5个字长"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span=6>
              <el-form-item label="年龄" prop="age">
                <el-input v-model="patient.age" placeholder="年龄范围是1-140岁"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span=6>
              <el-form-item label="性别" prop="sex">
                <el-radio-group v-model="patient.sex">
                  <el-radio label="男"></el-radio>
                  <el-radio label="女"></el-radio>
                </el-radio-group>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10">
              <el-col :span=6>
                <el-form-item label="身份证号" prop="idcard">
                  <el-input v-model="patient.idcard" placeholder="必填: 18位的二代身份证号码"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span=6>
                <el-form-item prop="createdate"  label="挂号日期" required>
                  <el-date-picker 
                    type="date"
                    placeholder="选择日期" 
                    v-model="patient.createdate" 
                    style="width: 100%;"
                    :picker-options="expireTimeOption"
                    value-format="yyyy-MM-dd"></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span=10>
                <el-form-item label="住址" prop="address">
                  <el-input v-model="patient.address" placeholder="省市区以及小区"></el-input>
                </el-form-item>
              </el-col>
          </el-row>
          
          <el-row :gutter="10">
              <el-col :span=6>
                <el-form-item label="科室" prop="deptid">
                  <el-select v-model="patient.deptid" placeholder="请选择科室" clearable @change="changeDoctor">
                    <el-option 
                      v-for="item in allDeptList"
                      :key="item.deptname"
                      :label="item.deptname"
                      :value="item.id">
                      </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span=6>
                <el-form-item label="医生" prop="doctorid">
                  <el-select v-model="patient.doctorid" placeholder="请选择医生" clearable :disabled="patient.deptid==''?true:false">
                    <el-option 
                      v-for="item in doctorList"
                      :key="item.dname"
                      :label="item.dname"
                      :value="item.id">
                      </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span=6>
                <el-form-item label="级别" prop="levelid">
                  <el-select v-model="patient.levelid" placeholder="请选择级别" @change="changeMoney">
                    <el-option 
                      v-for="item in levelList"
                      :key="item.id"
                      :label="item.levelname"
                      :value="item.id">
                      </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span=6>
                <el-form-item label="病历本" prop="delivery">
                  <el-tooltip :content="'状态: ' +(patient.delivery==1?'工本费1元':'已关闭')" placement="top">
                    <el-switch
                      v-model="patient.delivery"
                      active-value=1
                      inactive-value=0
                      :disabled="patient.levelid==''?true:false">
                    </el-switch>
                  </el-tooltip>
                </el-form-item>
              </el-col>
          </el-row>
          <el-row>
            <el-col :span=6>
              <el-form-item label="费用">
                  <el-tag type="success" style="font-size:20px" v-model="patient.cost" :value="patient.cost">{{patient.cost=getTotalPrice | showPrice}}</el-tag>
              </el-form-item>
            </el-col>
            <el-col :offset=12 :span=6>
              <el-form-item>
                <el-button type="primary" @click="submitForm('patient')">立即创建</el-button>
                <el-button @click="resetForm('patient')">重置</el-button>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </el-collapse-transition>
    <div style="margin-bottom:50px"></div>
    <!--********************************************华丽丽的分割线********************************************-->
    <el-divider content-position="left" @click="isshow2=!isshow2">
      <el-tooltip class="item" effect="dark" content="可收起下方模块" placement="bottom-start" :disabled="!isshow2">
        <el-button>挂号患者信息</el-button>
      </el-tooltip>
    </el-divider>
    <el-collapse-transition>
      <div v-show="isshow2">
        <el-table
          :header-cell-style="{
            'background-color': '#4d4c61',
            'color':'#fff'
          }"
          height="430"
          :data="patientList"
          v-loading="listLoading"
          element-loading-text="Loading"
          border
          style="width: 100%">
          <el-table-column
            prop="pid"
            label="病历号"
            width="180">
          </el-table-column>
          <el-table-column
            prop="pname"
            label="患者姓名"
            width="180">
          </el-table-column>
          <el-table-column
            prop="sex"
            label="性别"
            width="80"
            :filter-method="filterBySex"
            :filters="[{ text: '男', value: '男' }, { text: '女', value: '女' }]">
          </el-table-column>
          <el-table-column
            prop="age"
            label="年龄"
            width="100"
            sortable
            :filter-method="filterByAge"
            :filters="[{ text: '40岁以上', value: 1 }, { text: '40岁以下', value: 0 }]">
          </el-table-column>
          <el-table-column
            prop="idcard"
            label="身份证号">
          </el-table-column>
          <el-table-column
            prop="deptname"
            label="科室">
          </el-table-column>
          <el-table-column
            prop="dname"
            label="医师">
          </el-table-column>
          <el-table-column
            prop="createdatebystr"
            label="挂号日期">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 10px">{{ scope.row.createdatebystr }}</span>
            </template>
          </el-table-column>
          <el-table-column
            prop="cost"
            label="挂号费用"
            width="100">
            <template slot-scope="scope">
              <!--根据pstatus更改显示内容-->
              <el-tag
                :type="scope.row.cost*1 >10 ? 'success' : 'primary'"
                disable-transitions>{{scope.row.cost|showPrice}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column 
            prop="pstatus"
            label="患者状态" 
            :filter-method="filterTag"
            :filters="[{ text: '已诊疗', value: '0' }, { text: '待诊疗', value: '1' }, { text: '已退号', value: '2' }]"
            filter-placement="bottom-end"
          >
            <!--filter-method是一个筛选方法，他根据设置的filter的value进行筛选
                filters是一个数组，里面的text是页面能够看到的筛选条件名称，value是用筛选的值-->
            <template slot-scope="scope">
              <!--根据pstatus更改显示内容-->
              <el-tag
                :type='scope.row.pstatus*1===1?"warning":(scope.row.pstatus*1===2?"info":"success")'
                disable-transitions>{{scope.row.pstatus | showPstatus}}</el-tag>
            </template>
          </el-table-column>
        </el-table>
        <div class="block" align="right" style="margin-top:10px;background-color:#d4dcd6">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageBean.currentPage"
            :page-sizes="pageBean.pageSizes"
            :page-size="pageBean.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pageBean.total">
          </el-pagination>
        </div>
      </div>
    </el-collapse-transition>
    
  </div>
</template>


<script>
import Patient from "@/api/patient"
  // 导入省市区三级联动数据
  export default {
    data() {
      return {
        patient: {
          pid: "",
          pname: "",
          sex: "男",
          age: "",
          idcard: "",
          createdate: "",
          address: "",
          doctorid: "",
          levelid: "",
          deptid: "",
          delivery:0,
          money:0,
          cost: 0,
        },
        isshow:true,
        isshow2:true,
        allDeptList:[],
        doctorList:[],
        levelList:[],
        // 设置当前日期前的日期不可用
        expireTimeOption: {
					disabledDate(date) {
			          	//disabledDate 文档上：设置禁用状态，参数为当前日期，要求返回 Boolean
						return date.getTime() < Date.now() - 24 * 60 * 60 * 1000;
					}
				},
        deadline: "",
        rules: {
          pid: [
            { required: true, message: '请输入病历号', trigger: 'blur'}
          ],
          pname: [
            { required: true, message: '请输入姓名', trigger: 'blur' },
            { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
          ],
          sex: [
            { required: true, message: '请选择性别', trigger: 'change' }
          ],
          age: [
            { required: true, message: '请填写年龄', trigger: 'blur' },
            { min: 1, max: 3, message: '年龄不合法', trigger: 'blur',pattern:/^(?:[1-9][0-9]?|1[0123][0-9]|140)$/}
          ],
          idcard: [
            { required: true, message: '请输入身份证号', trigger: 'blur' },
            { min: 18, max: 18, message: '身份证长度为18位', trigger: 'change' },
            { message: '身份证号码不合法',trigger: 'blur',pattern:/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/ }
          ],
          createdate: [
            {  required: true, message: '请选择日期', trigger: 'change' }
          ],
          address:[
            {required:true, message:"请输入住址", trigger:'blur'}
          ],
          doctorid: [
            { required: true, message: '请选择医生', trigger: 'change' }
          ],
          levelid: [
            { required: true, message: '请选择级别', trigger: 'change' }
          ],
          deptid: [
            { required: true, message: '请选择科室', trigger: 'change' }
          ]
        },
        listLoading: false,
        patientList: [],
        pageBean:{
          total: 0,
          pageSizes:[5,10,15,20],
          pageSize:5,
          currentPage:1,
        },
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            Patient.saveRegisteredInDB(this.patient).then(response=>{
              console.log(response);
              //判断后台是否存入成功
              if(response.code==20000){
                 this.$message({
                    message: '挂号成功！',
                    type: 'success',
                    center:true
                  });
                  // 插入成功 更新页面
                  this.fetchDataPage(this.pageBean.currentPage,this.pageBean.pageSize);
                  // 清空遗留缓存
                  this.resetForm('patient');
              }else{
                  this.$message({
                    message: '挂号失败，请联系开发人员',
                    type: 'error',
                    center:true
                  });
                  return false;
              }
            })
          } else {
            return false;
          }
          console.log(this.patient);
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
        // 更新挂号pid
        this.getPid();
        this.patient.money=0;
        this.patient.cost=0;
      },
     getPid(){
       Patient.getPid().then(res=>{
        // 获取后台病历号
        this.patient.pid=res.data.pid;
       });
     },
     getAllDeptList(){
       Patient.getAllDeptList().then(res=>{
         // 获取后台所有的科室
        this.allDeptList=res.data.allDeptList;
       });
     },
     changeDoctor(val){
       // @change事件 获取到选中科室的id
       // 清除缓存
       this.doctorList=[];
       this.patient.doctorid=""
      //获取医生列表
      if(val){
        Patient.getDoctorListByDeptid(val).then(res=>{
         // 根据选中的科室获取后台医生列表
         this.doctorList=res.data.doctorList;
       })
      }
     },
     changeMoney(val){
       if(val!=''){
         for (var i=0;i<this.levelList.length;i++) {
           if(val==this.levelList[i].id){
             this.patient.money=this.levelList[i].cost*1;
             break;
           }
         }
       }
     },
     getLevelList(){
        Patient.getLevelList().then(res=>{
          // 获取后台所有的级别
          this.levelList=res.data.levelList;
        });
     },
     fetchDataPage(page=1,pageSize) {
        // 此方法更新表格数据
        pageSize=this.pageBean.pageSize
        this.listLoading = true
        Patient.getPatientListPage(page,pageSize).then(response => {
          console.log(response);
          this.patientList=response.data.patientList;
          this.pageBean.total=response.data.total;
          this.listLoading = false;
        })
      },
      handleSizeChange(val) {
        //监听当前页容量
        this.pageBean.pageSize=val;
        // 由于切换了页面显示记录容量，需要更新页面数据
        console.log("当前页容量: "+val,"当前页码: "+this.pageBean.currentPage);
        this.fetchDataPage(this.pageBean.currentPage,val*1);
      },
      handleCurrentChange(val) {
        // 监听当前页码
        this.pageBean.currentPage=val;
        console.log("当前页容量: "+this.pageBean.pageSize,"当前页码: "+val);
        this.fetchDataPage(val*1,this.pageBean.pageSize);
      },
      stateFormat(row, column) {
        return row.pstatus==="1"?"ok":"no";
      },
      filterTag(value, row) {
        return row.pstatus === value;
      },
      filterBySex(value, row) {
        return row.sex === value;
      },
      filterByAge(value, row) {
        return value===1?row.age>=40:row.age<40;
      },
    },
    created(){
      this.getPid();
      this.getAllDeptList();
      this.getLevelList();
      this.fetchDataPage();
    },
    filters:{
        // 过滤器
        showPrice(value){
            return '￥'+(value>9?'':'0')+value.toFixed(2)
        },
        // 状态过滤
        showPstatus(value){
          switch(value*1){
            case 0:
              return "已诊疗";
              break;
            case 1:
              return "待诊疗";
              break;
            case 2:
              return "已退号";
              break;
          }
        }
    },
    computed:{
        getTotalPrice(){
            return this.patient.money+this.patient.delivery*1;
        }
    },
  }
</script>