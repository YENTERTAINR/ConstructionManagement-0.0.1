import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";

// 查询用户个人档案
export function getUserInfo(userId) {
  return request({
    url: '/user/info/user/' + praseStrEmpty(userId),
    method: 'get'
  })
}

// 编辑用户个人档案
export function editInfo(userInfo) {
  return request({
    url: '/user/info/edit/',
    method: 'post',
		data:userInfo
  })
}

//官网通知公告
export function listNotice(query) {
  return request({
    url: '/user/info/notice',
    method: 'get',
    params: query
  })
}