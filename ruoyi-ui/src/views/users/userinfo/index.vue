<template>
	<div class="app-container">
		<el-row :gutter="20">
			<el-col :span="6" :xs="24">
				<el-card class="box-card">
					<div slot="header" class="clearfix">
						<span>个人信息</span>
						<ul class="list-group list-group-striped">
							<li class="list-group-item">
								<svg-icon icon-class="user" />用户名称
								<div class="pull-right" v-if="!isUsers">
									{{ users.userName }}
								</div>
								<div v-if="isUsers">
								<el-input v-model="users.userName" v-if="isUsers"></el-input>
								</div>
							</li>
							<li class="list-group-item">
								<svg-icon icon-class="phone" />手机号码
								<div class="pull-right" v-if="!isUsers">
									{{ users.phonenumber }}
								</div>
								<div v-if="isUsers">
								<el-input v-model="users.phonenumber" v-if="isUsers"></el-input>
								</div>
							</li>
							<li class="list-group-item">
								<svg-icon icon-class="email" />用户邮箱
								<div class="pull-right" v-if="!isUsers">
									{{ users.email }}
								</div>
								<div v-if="isUsers">
								<el-input v-model="users.email" v-if="isUsers"></el-input>
								</div>
							</li>
							<li class="list-group-item">
								<svg-icon icon-class="tree" />学校
								<div class="pull-right" v-if="users.dept">
									{{ user.school }}
								</div>
							</li>
							<li class="list-group-item">
								<svg-icon icon-class="tree" />所属部门
								<div class="pull-right" v-if="users.dept">
									{{ users.dept.deptName }} 
								</div>
							</li>
							<li class="list-group-item">
								<svg-icon icon-class="peoples" />所属角色
								<div class="pull-right">
									{{ roleGroup }}
								</div>
							</li>
							<li class="list-group-item">
								<svg-icon icon-class="date" />创建日期
								<div class="pull-right">2018-08-23 09:11:56</div>
							</li>
							<!-- <li class="list-group-item">
								<el-button type="primary" round @click="usersSubmit">保存</el-button>
								<el-button round @click="usersEdit">编辑</el-button>
							</li> -->
						</ul>
					</div>
				</el-card>
			</el-col>
			<el-col :span="18" :xs="24">
				<el-card>
					<div slot="header" class="clearfix">
						<span>基本资料</span>
					</div>
					<el-tabs v-model="activeName">
						<el-tab-pane label="个人档案" name="userinfo">
							<el-row :gutter="20">
							  <el-col :span="6" :offset="3"><div class="grid-content bg-purple">
							  <span>学号:</span>
							  <span v-if="!isEdit">{{ user.stu_no}}</span>
							  <el-input v-model="user.stu_no" v-if="isEdit"></el-input>
							  </div></el-col>
							  <el-col :span="6" :offset="3"><div class="grid-content bg-purple">
							  <span>姓名:</span>
							  <span v-if="!isEdit">{{ user.userName}}</span>
							  <el-input v-model="user.userName" v-if="isEdit"></el-input>
							  </div></el-col>
							</el-row>
							<el-row :gutter="20">
							  <el-col :span="7" :offset="3"><div class="grid-content bg-purple">
							  <span>身份证号:</span>
							  <span v-if="!isEdit">{{ user.idNumber}}</span>
							  <el-input v-model="user.idNumber" v-if="isEdit"></el-input>
							  </div></el-col>
							  <el-col :span="6" :offset="2"><div class="grid-content bg-purple">
							  <span>民族:</span>
							  <span v-if="!isEdit">{{ user.nation}}</span>
							  <el-input v-model="user.nation" v-if="isEdit"></el-input>
							  </div></el-col>
							</el-row>
							
							<el-row :gutter="20">
							  <el-col :span="6" :offset="3"><div class="grid-content bg-purple">
							  <span>政治面貌:</span>
							  <span>{{ postGroup }}</span>
							  </div></el-col>
							  <el-col :span="6" :offset="3"><div class="grid-content bg-purple">
							  <span>入学时间:</span>
							  <span>{{ starttime}}</span>
							  </div></el-col>
							</el-row>
							
							<el-row :gutter="20">
							  <el-col :span="6" :offset="6"><div>
							   <el-button type="primary" round @click="submit">保存</el-button>
							   <el-button round @click="edit">编辑</el-button>
							  </div></el-col>
							  
							</el-row>
							
						</el-tab-pane>
						
					</el-tabs>
				</el-card>
			</el-col>
		</el-row>
	</div>
</template>

<script>
	import { getUserProfile } from "@/api/system/user";
	import { getUserInfo,editInfo } from "@/api/user/userinfo";
	import { parseTime } from "@/utils/ruoyi";
	import { getInfo } from "@/api/login";
	export default {
		name: "Profile",
		data() {
			return {
				user: {
					userId:undefined,
					stu_no:'',
					userName:'',
					idNumber:'',
					nation:'',
					political:'',
					startTime:'2020-01-01',
					school:'广西科技大学鹿山学院'
				},
				starttime:'2020-01-01',
				activeName:'userinfo',
				isEdit:false,
				isUsers:false,
				users:{},
				roleGroup: {},
				postGroup: {}
			};
		},
		created() {
			getInfo().then(res=>{
				this.user.userId = res.user.userId
				this.getUser();
			})
		},
		methods: {
			getUser() {
				getUserProfile().then(res=>{
					this.users = res.data
					// console.log(res)
					this.roleGroup = res.roleGroup;
					this.postGroup = res.postGroup;
				});
				console.log(this.user.userId)
				getUserInfo(this.user.userId).then(res=>{
					// console.log(res)
					if(res.data !== null){
					this.user = res.data
					if(this.user.startTime == null)return
					this.user.startTime = parseTime(this.user.startTime,'{y}-{m}-{d} ')
					}
				})
			},
			edit(){
				this.isEdit=!this.isEdit
			},
			usersEdit(){
				this.isUsers = !this.isUsers
			},
			submit(){
				if(this.isEdit==true){
					this.isEdit=!this.isEdit
				}
				// this.user.userId = window.sessionStorage.getItem("username")
				editInfo(this.user).then(res =>{
					console.log(res)
					if(res.code == 200)return this.msgSuccess("保存成功")
					this.msgError("保存失败")
				})
				
			},
			usersSubmit(){
				if(this.isUsers==true){
					this.isUsers = !this.isUsers
				}
			}
		}
	};
</script>

<style>
  .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>