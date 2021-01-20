<template>
  <div class="app-container">
    <!--********************************************华丽丽的分割线********************************************-->

    <el-divider content-position="left" @click="isshow=!isshow">
      <el-tooltip class="item" effect="dark" content="可收起下方模块" placement="bottom-start" :disabled="!isshow">
        <el-button>挂号患者信息</el-button>
      </el-tooltip>
    </el-divider>
    <el-collapse-transition>
      <div v-show="isshow">
        <el-table
          :header-cell-style="{
            'background-color': '#4d4c61',
            'color':'#fff'
          }"
          height="600"
          :data="patientList.filter(data => !search || data.pid.toString().toLowerCase().includes(search.toLowerCase()))"
          v-loading="listLoading"
          element-loading-text="Loading"
          border
          style="width: 100%">
          <el-table-column
            prop="pid"
            label="病历号"
            width="180">
            <template slot="header">
              <el-dropdown trigger="hover" @command="handleCommand">
              <span class="el-dropdown-link" >
                病历号<i class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="1">全局模糊搜索</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
            </template>
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
          <el-table-column align='center'>
            <template slot="header">
              <el-input
                v-model="search"
                size="mini"
                placeholder="快速检索当页病历号"/>
            </template>
             <template slot-scope="scope">
              <el-button
                size="mini"
                :type=' scope.row.pstatus*1!=1? "info":"danger"'
                @click="openConfirm(scope.$index, scope.row)" 
                :disabled="scope.row.pstatus*1!=1"
                :loading="currentIndex==scope.$index"
                round>{{currentIndex==scope.$index?"退号中":"点我退号"}}</el-button>
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
    <!-- 搜索弹出框 -->
    <el-dialog title="患者查询" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="tempP" :rules="rules" ref="tempP">
        <el-form-item label="全局模糊查询" :label-width="formLabelWidth" prop='pid'>
          <el-input v-model="tempP.pid" autocomplete="off" placeholder="请输入病例号相关数字"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('tempP')">取 消</el-button>
        <el-button type="primary" @click="submitForm('tempP')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>


<script>
import Patient from "@/api/patient"
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
        tempP:{
          pid:""
        },
        rules:{
          pid: [
            { required: true, message: '请输入病历号', trigger: 'blur'},
            { message:'输入内容必须是2-9位的数字',pattern:/^[0-9]{2,9}$/,trigger:'change' }
          ],
        },
        formLabelWidth: '120px',
        search:'',
        currentIndex:-1,
        isshow:true,
        listLoading: false,
        dialogFormVisible: false,
        patientList: [],
        pageBean:{
          total: 0,
          pageSizes:[5,10,15,20],
          pageSize:10,
          currentPage:1,
        },
      };
    },
    methods: {
      openConfirm(index,currentRow) {
        this.currentIndex=index;
        this.$confirm('即将为患者退号, 是否继续?', '提示', {
          confirmButtonText: '继续',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.setBackno(index,currentRow);
          this.currentIndex=-1;
        }).catch(() => {
          this.currentIndex=-1;
          this.$message({
            type: 'info',
            message: '已取消退号'
          });          
        });
      },
      setBackno(index,currentRow){
        Patient.setBacknoPatient(currentRow.pid).then(resp=>{
          if(resp.data.code===20000){
            this.$message({
              message: '退号成功',
              type: 'success',
              center:true
            });
            
          }else if(resp.data.code===10001){
            this.$message({
              showClose: true,
              message: '警告: 不能重复退号',
              type: 'warning'
            });
          }else if(resp.data.code===10000){
            this.$message({
              showClose: true,
              message: '错误: 后台未能获取到pid',
              type: 'error'
            });
          }
          // 更新页面
          this.fetchDataPage(this.pageBean.currentPage,this.pageBean.pageSize);
        })
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if(valid){
            // 前往后台将包含pid相关数字的结果返回
            Patient.getPatientListPageLikePid(this.tempP.pid,1,this.pageBean.total).then(resp=>{
              console.log("模糊查询",resp);
              if(resp.data.code=="20000"){
                // 查询到匹配信息
                this.patientList=resp.data.patientList;
                this.pageBean.total=resp.data.total;
                var flag=true;
                for(var i=0;i<this.pageBean.pageSizes.length;i++){
                  if(this.pageBean.pageSizes[i]>this.pageBean.total){
                    this.pageBean.pageSize=this.pageBean.pageSizes[i];
                    flag=false;
                    break;
                  }
                }
                if(flag){
                  this.pageBean.pageSize=this.pageBean.total;
                  this.pageBean.pageSizes.push(this.pageBean.pageSize);
                }
                this.$message({
                  showClose: true,
                  message: '查询成功: 数据如下标所示(刷新后数据会跟着刷新)',
                  type: 'success'
                });
              }else if(resp.data.code=="20001"){
                this.$message({
                  showClose: true,
                  message: '错误: 模糊查询未获取到pid参数',
                  type: 'error'
                });
              }else if(resp.data.code=="20002"){
                this.$message({
                  showClose: true,
                  message: '警告: 后台暂未查到PID：'+this.tempP.pid+"的患者信息",
                  type: 'warning'
                });
              }
            })
            this.dialogFormVisible=false;
            this.resetForm('tempP');
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
        this.dialogFormVisible = false ;
      },
      fetchDataPage(page=1,pageSize) {
        // 此方法更新表格数据
        pageSize=this.pageBean.pageSize
        this.listLoading = true
        Patient.getBackableListPage(page,pageSize).then(response => {
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
       handleCommand(command) {
        if(command == "1"){
          this.dialogFormVisible=true;
        }
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
<style>
  .el-dropdown-link {
    cursor: pointer;
    color: #38b48b;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>