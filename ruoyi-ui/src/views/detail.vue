<template>
    <div class="container">
        <div class="content_container">
            <div class="type">
                {{newsType}}
            </div>
            <div class="title">
                {{news.noticeTitle}}
                <div class="about">来源：{{news.createBy}} 时间：{{news.createTime}} 作者： {{news.createBy}} </div>
            </div>
            <div class="content" v-html="news.noticeContent">
            </div>
        </div>
    </div>
</template>

<script>
import { getNotice } from "@/api/system/notice";
  export default {
    name: 'detail',
		data(){
			return{
				noticeId:'',
				news:{},
				newsType:''
			}
		},
		methods:{
			getNews(){
				this.noticeId = this.$route.query.noticeId
				getNotice(this.noticeId).then(res=>{
					this.news = res.data
					if(this.news.noticeType ==1||this.news.noticeType ==2) this.newsType = '通知公告'
					if(this.news.noticeType ==3) this.newsType = '支部要闻'
					if(this.news.noticeType ==4) this.newsType = '支部动态'
					// console.log(this.news)
				})
				
			}
		},
		created(opt) {
			this.$nextTick(function(){
			      this.getNews()
			 });
			
		}
  }
</script>

<style scoped>
  .about{
    width: 100%;
    height: 20px;
    position: absolute;
    font-size: 20px;
  }
  .content{
    width: 100%;
    position: absolute;
    top: 200px;
    font-size: 20px;
  }
  .title{
    text-align: center;
    line-height: 60px;
    width: 100%;
    height: 100px;
    position: absolute;
    margin-top: 60px;
    font-size: 30px;
  }
  .type{
      width: 100%;
      height: 100px;
      position: absolute;
      margin-top: 20px;
      font-size: 35px;
      color: blue;
  }
  .content_container{
    width: 1200px;
    min-width: 1200px;
    height: 100%;
    position: relative;
    margin: 0 auto;
  }
  .container{
    width: 100%;
    min-width: 1200px;
    height: 100%;
    position: absolute;
  }
</style>
