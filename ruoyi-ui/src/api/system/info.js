import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/ruoyi";


export function listInfo() {
  return request({
    url: '/system/userManage/list',
    method: 'get',
  })
}

// 查询档案详细
export function getInfo(userId) {
  return request({
    url: '/system/userManage/' + praseStrEmpty(userId),
    method: 'get'
  })
}

// 删除公告
export function delInfo(userIds) {
  return request({
    url: '/system/userManage/' + userIds,
    method: 'delete'
  })
}