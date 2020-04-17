<template>
  <div class="container">
      <div class="container_top">
         <div class="top_container">
           <div class="top_left">
                <a href="javascript:;">
                  <img class="logo_img" src="../assets/image/logo.gif">
                </a>
           </div>
           <div class="top_right">
               <a href="javascript:;">
				   <img src="../assets/image/in_logo.png" height="100%" width="85%"/>
                <div class="bt_login" >
                    <el-button type="primary" round @click="logoClick">登录</el-button>
                </div>
               </a>

           </div>
         </div>

      </div>
      <div class="container_middle">
          <div class="mid_scroll">

			<!-- 查看通知 -->
			<el-dialog
			  title="详情"
			  :visible.sync="noticeView"
			  width="70%"
			  >
			  <div style="text-align: center;"><h3>{{title}}</h3></div>
			  <p v-html="content"></p>
			  <span slot="footer" class="dialog-footer">
			    <el-button @click="noticeView = false">关 闭</el-button>
			  </span>
			</el-dialog>
			
            <div class="block">
				<el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal"
				  background-color="#545c64"
				  text-color="#fff"
				  active-text-color="#ffd04b">
				  <el-menu-item index="1">首页
				  </el-menu-item>
				  <el-menu-item index="2">
					  <a href="http://www.lzls.gxust.edu.cn/xszx/">学生服务</a>
				  </el-menu-item>
				  <el-menu-item index="3">
					  <a href="http://www.lzls.gxust.edu.cn/szw/">思想政治</a>
				  </el-menu-item>
				  <el-menu-item index="4">
					  <a href="http://www.lzls.gxust.edu.cn/xxgk.htm">信息公开</a>
					  </el-menu-item>
					  
				  </el-menu>
              <!--<span class="demonstration">Click 指示器触发</span>-->
              <el-carousel trigger="click" height="340px">
                 <el-carousel-item v-for="(it,index) in imgsList" :key="index">
                       <img :src='it.ressrc' width="100%"/>
                      </el-carousel-item>
              </el-carousel>
            </div>
          </div>
          <div class="mid_content">
              <div class="mid_content_left">
				   <el-tabs v-model="deftab" type="border-card">
				      <el-tab-pane label="党建信息" name="first">
						  <el-row :gutter="20" class="Listcontext" v-for="item in newsList">
						    <el-col :span="3"><div class="grid-content bg-purple">
							<img src="../assets/logo/logo.png"/>
							</div></el-col>
						    <el-col :span="18"><div class="grid-content bg-purple">
							 <el-link @click="getnews(item)">{{item.title}}</el-link>
							 <div class="content">
									{{item.content}}
							 </div>
							</div></el-col>
						  </el-row>
					  </el-tab-pane>
				      <el-tab-pane label="通知公告" name="second">
						  <el-row :gutter="20" class="Listcontext" v-for="item in noticeList">
						    <el-col :span="18"><div class="grid-content bg-purple">
						  							 <el-link @click="getnews(item)">{{item.title}}</el-link>
						  							 <div class="content">
						  									<div>{{item.content}}</div>
						  							 </div>
						  							</div></el-col>
						  </el-row>
					  </el-tab-pane>
				      <el-tab-pane label="学校新闻" name="third">
						  <el-row :gutter="20" class="Listcontext" v-for="item in dynamicList">
						    <el-col :span="18"><div class="grid-content bg-purple">
						  							 <el-link @click="getnews(item)">{{item.title}}</el-link>
						  							 <div class="content">
															 {{item.content}}
						  							 </div>
						  							</div></el-col>
						  </el-row>
					  </el-tab-pane>
					  
						  <el-tab-pane label="党员管理" name="fo">
						  <el-row :gutter="20" style="margin-top: 20px;">
						    <el-col :span="9">
								姓名：<el-input></el-input>
						  	</el-col>
						  </el-row>
							<el-row :gutter="20" style="margin-top: 20px;">
							  <el-col :span="9">
								身份证号：<el-input></el-input>
								</el-col>
							</el-row>
							<el-row style="margin-top: 20px;">
								<el-button type="primary" round>查 询</el-button>
							</el-row>
							
						</el-tab-pane>
				    </el-tabs>
					
                </el-collapse>
              </div>
            <!-- <div class="mid_content_right">
              <div class="mid_content_right_left">
                    <div class="module">
                      学生服务
                    </div>
                    <div class="module" @click="toPolitics">
                      思想政治
                    </div>
              </div>
              <div class="mid_content_right_right">
                    <div class="module">
                      支部工作
                    </div>
                    <div class="module">
                      信息公开
                    </div>
              </div>
            </div> -->
          </div>
      </div>
      <div class="container_bottom">
          <div class="announce">
            <p>版权所有：柳州工学院   地址：中国·广西柳州市鱼峰区新柳大道99号    党政办公室电话：0772-3517068；招生咨询电话：0772-3516196 3517685 ，就业咨询电话：0772-3517066</p>
          </div>
      </div>
  </div>
</template>

<script>
import { listNotice } from "@/api/user/userinfo";
  export default {
    name: 'default',
    methods:{
      logoClick(){
        window.location.replace(`login`)
      },
		  // 思想政治
	  toPolitics(){
		  window.location.replace(`politics`)
	  },
	  getList(){
		  // 获取通知公告列表
		  listNotice(this.queryParams).then(res => {
		    // console.log(res.rows)
			this.List = res.rows
			this.List.forEach(item =>{
				if(item.noticeType == 1||item.noticeType ==2) {
					this.noticeList.push({noticeId:item.noticeId,title:item.noticeTitle,content:item.noticeContent.replace(/<[^>]+>/g,"")})
				}
				if(item.noticeType == 3){
					this.newsList.push({noticeId:item.noticeId,title:item.noticeTitle,content:item.noticeContent.replace(/<[^>]+>/g,"")})
				}
				if(item.noticeType == 4){
					this.dynamicList.push({noticeId:item.noticeId,title:item.noticeTitle,content:item.noticeContent.replace(/<[^>]+>/g,"")})
				}
			})
		  });
	  },
	  getnews(rows){
		  // this.noticeView = true
		  // console.log(rows)
		  // this.title = rows.title
		  // this.content = rows.content
		   // window.location.replace(`detail?rows=`+rows.noticeId)
			const { href } = this.$router.resolve({
				name:'detail',
				query:{
					noticeId:rows.noticeId
				}
			})
			window.open(href, '_blank');
	  }
    },
	created() {
		this.$nextTick(function(){
		      this.getList()
		 });
	},
    data(){
		return{
		  mark:0,
		  imgsList:[
			 { index:1,ressrc:require('../assets/image/1.jpg')},
			  { index:2,ressrc:require('../assets/image/2.jpg')},
			  { index:3,ressrc:require('../assets/image/3.jpg')},
			  { index:4,ressrc:require('../assets/image/4.jpg')}],
		  deftab:'first',
		  activeIndex:'1',
		  List:[],
		  noticeView:false,
		  title:'',
		  content:'',
		  newsList:[],
		  noticeList:[],
		  dynamicList:[],
		  queryParams: {
		    pageNum: 1,
		    pageSize: 10,
		    noticeTitle: undefined,
		    createBy: undefined,
		    status: undefined
		  },
	  }
    }
  }
</script>

<style scoped>
  .announce{
    text-align: center;
    line-height: 100px;
    font-size: 12px;
  }
  .module{
	cursor: pointer;
    margin-top:5px;
    width:100%;
    height: 100px;
    background-color: #1C84C6;
    line-height: 100px;
    text-align: center;
    font-size: 25px;
	color: white;
	border: 1px solid #CCC;
	border-radius: 20px;
  }
  .mid_content_right_left{
      display: inline-block;
      position: absolute;
      width: 200px;
      height: 800px;
      background-color: whitesmoke;
  }
  .mid_content_right_right{
    display: inline-block;
    position: absolute;
    margin-left: 205px;
    width:190px;
    height: 800px;
    background-color:whitesmoke;
  }
  .scroll_index{
    z-index: 5;
    position: absolute;
  }
  .scroll_img{
      width:1200px;
    height:400px;
  }
  .logo_img{
    width: 600px;
    height:100px;
  }
  .bt_login{
    position: absolute;
    width: 100px;
    height: 40px;
    /* background-color: #1c84c6; */
    text-align: center;
    font-size: 25px;
    line-height: 40px;
    font-family: "微软雅黑 Light";
    top:30px;
    right: 0px;
  }

  .top_container{

    margin: 0 auto;
    width: 1200px;
    height: 100px;
  }
  .top_left{
    position: relative;
    display: inline-block;
    width:600px;
    height:100px;
    background-color: #1c84c6;
  }
  ::-webkit-scrollbar {
    width: 0px;
    height: 0px;
  }
  .top_right{
    display: inline-block;
    position: relative;
    right: 0px;
    width:595px;
    height:100px;
    margin-top:0px;
    background-color:#DFE4ED;
	/* background-image: url(../assets/image/in_logo.png);
	background-size: 100% 100%; */
  }
  .mid_content_left{
	  padding: 40px 10px 0px 0px;
    position: absolute;
    overflow: auto;
    overflow-y:scroll;
    overflow-x:hidden;
    display: inline-block;
      width: 1200px;
      height: 800px;
    background-color: whitesmoke;
  }
  .mid_content_right{
    position: absolute;
    display: inline-block;
    width: 395px;
    height: 800px;
    background-color: whitesmoke;
    margin-left: 800px;
  }
  .mid_content{
    margin: 0 auto;
    width: 1200px;
    height: 800px;
    background-color: whitesmoke;
  }
  .mid_scroll{
    margin: 0 auto;
    width: 1200px;
    height: 400px;
    background-color: #888888;
  }

  .container_middle{
    width: 100%;
    min-width:1200px;
    height: 1200px;
    /* background-color: #F1FAFA; */
  }
  .container_bottom{
    width: 100%;
    min-width:1200px;
    height: 100px;
    background-color: #f4f4f5;
  }
  .container_top{
	  margin-bottom: 30px;
    width: 100%;
    min-width:1200px;
    height: 100px;
    background-color: #F1FAFA;
  }
    *{
      margin: 0;
      padding: 0;
    }
  .container{
    position: absolute;
    width: 100%;
    min-width:1200px;
    height: 1400px;
    background-color: #F1FAFA;
  }
  .el-menu-item{
	  width: 150px;
	  align-items: center;
	  align-content: center;
	  text-align: center;
  }
  .grid-content{
	  padding: 10px 0px 10px 0px;
  }
  .Listcontext{
	  border-bottom: 1px solid #ccc;
  }
  .content{
	   display: -webkit-box;/*作为弹性伸缩盒子模型显示*/
	      -webkit-line-clamp: 2; /*显示的行数；如果要设置2行加...则设置为2*/
	      overflow: hidden; /*超出的文本隐藏*/
	      text-overflow: ellipsis; /* 溢出用省略号*/
	      -webkit-box-orient: vertical;/*伸缩盒子的子元素排列：从上到下*/
		  font-size: 16px;
		  color: #ab9d96;
  }
</style>
