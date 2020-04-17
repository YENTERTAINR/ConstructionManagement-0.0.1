package com.ruoyi.project.system.service;

import com.ruoyi.project.user.domain.UserInfo;

import java.util.List;

public interface ISysUserInfoService {
    /**
     *
     *
     * @return 角色所有档案信息
     */
    public List<UserInfo> selectInfoList();

    /**
     *
     * 根据ID查询角色档案信息
     */
    public UserInfo selectInfoById(String userId);

    /**
     * 批量删除档案信息
     *
     * @param userIds 需要删除的角色ID
     * @return 结果
     */
    public int delInfo(String[] userIds);
}
