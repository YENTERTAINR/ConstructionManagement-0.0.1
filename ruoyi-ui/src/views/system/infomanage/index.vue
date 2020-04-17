<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true">
      <el-form-item label="姓名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入姓名"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学号" prop="stu_no">
        <el-input
          v-model="queryParams.stu_no"
          placeholder="请输入学号"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="民族" prop="nation">
        <el-input
          v-model="queryParams.nation"
          placeholder="请输入民族"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="List" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
	  <el-table-column label="昵称" prop="userId" width="120" />
      <el-table-column label="学号" prop="stu_no" width="120" />
      <el-table-column label="姓名" prop="userName" :show-overflow-tooltip="true" width="150" />
      <el-table-column label="民族" prop="nation" :show-overflow-tooltip="true" width="150" />
      <el-table-column label="入学时间" align="center" prop="startTime" width="180">
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:role:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
	
	<!-- 新增或修改对话框 -->
	<el-dialog :title="title" :visible.sync="open" width="500px">
		<el-form ref="form" :model="form">
			<el-row>
			    <el-col :span="18">
			      <el-form-item label="姓名">
			        <el-input v-model="form.userName" placeholder="请输入姓名" />
			      </el-form-item>
			    </el-col>
				  <el-col :span="18">
				    <el-form-item label="学号">
				      <el-input v-model="form.stu_no" placeholder="请输入学号" />
				    </el-form-item>
				  </el-col>
				  <el-col :span="18">
				    <el-form-item label="民族">
				      <el-input v-model="form.nation" placeholder="请输入民族" />
				    </el-form-item>
				  </el-col>
				  <el-col :span="18">
				    <el-form-item label="身份证">
				      <el-input v-model="form.idNumber" placeholder="请输入身份证号码" />
				    </el-form-item>
				  </el-col>
				  <el-col :span="18">
				    <el-form-item label="入学时间">
				      <el-date-picker
				            v-model="form.startTime"
				            type="datetime"
				            placeholder="选择日期时间">
				          </el-date-picker>
				    </el-form-item>
				  </el-col>
			  </el-row>
		</el-form>
		<div slot="footer" class="dialog-footer" style="padding-top:20px">
		  <el-button type="primary" @click="submitForm">确 定</el-button>
		  <el-button @click="cancel">取 消</el-button>
		</div>
	</el-dialog>
  </div>
</template>

<script>
import { listInfo,getInfo,delInfo } from "@/api/system/info";
import { editInfo } from "@/api/user/userinfo.js";
import { parseTime } from "@/utils/ruoyi";
export default {
  name: "userInfo",
  data() {
    return {
      queryParams:{},
	  List:[],
      single:true,
	  multiple:true,
	  loading:false,
	  title:'',
	  open:false,
	  form:{},
	  ids:[],// 选中数组
    };
  },
  created() {
    this.getList()
  },
  methods: {
   getList(){
	   this.loading = true
	   listInfo().then(res=>{
		   this.loading = false
		   // console.log(res)
		   this.List = res.data
		   this.List.forEach(item =>{
			   item.startTime = parseTime(item.startTime,'{y}-{m}-{d} ')
		   })
	   })
   },
   // 搜索
   handleQuery(){
	   
   },
   // 重置
   resetQuery(){
	   this.resetForm("queryForm");
	   this.handleQuery();
  },
  // 新增档案
  handleAdd(){
	  this.reset()
	  this.title = '新增档案'
	  this.open = true
  },
  // 修改档案
  handleUpdate(rows){
	  this.reset()
	  this.title = '修改档案'
	  this.open = true
	  const userId = rows.userId || this.ids
	  getInfo(userId).then(res=>{
		  // console.log(res)
		  this.form = res.data
	  })
  },
  // 取消按钮
  cancel(){
	  this.reset()
	  this.open = false
  },
  // 表单重置
  reset(){
	  this.form = {}
	  this.resetForm("form")
  },
  // 删除
  handleDelete(rows){
	  const userIds = rows.userId || this.ids;
	  this.$confirm('是否确认删除角色编号为"' + userIds + '"的数据项?', "警告", {
	      confirmButtonText: "确定",
	      cancelButtonText: "取消",
	      type: "warning"
	    }).then(function() {
			// console.log(userIds)
	      return delInfo(userIds);
	    }).then(() => {
	      this.getList();
	      this.msgSuccess("删除成功");
	    }).catch(function() {
			
		});
  },
  // 
  handleSelectionChange(selection){
	  this.ids = selection.map(item => item.userId)
	  this.single = selection.length!=1
	  this.multiple = !selection.length
  },
  // 提交档案
  submitForm(){
	  if(this.form.userId !== null){
		  editInfo(this.form).then(res =>{
			  if(res.code == 200){
				  this.msgSuccess('更新数据成功')
				  this.reset()
				  this.open = false
				  this.getList()
			  }
		  })
	  }
  }
  }
};
</script>