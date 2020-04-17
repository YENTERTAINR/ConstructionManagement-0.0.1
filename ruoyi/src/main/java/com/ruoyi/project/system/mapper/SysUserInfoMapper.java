package com.ruoyi.project.system.mapper;

import com.ruoyi.project.user.domain.UserInfo;

import java.util.List;

public interface SysUserInfoMapper {

    /**
     * 查询所有档案信息
     *
     * @return 档案信息列表
     */
    public List<UserInfo> selectInfoList();

    /**
     * 根据ID查询档案信息
     *
     * @return UserInfo
     */
    public UserInfo selectInfoById(String userId);

    /**
     * 批量删除档案信息
     *
     * @param userId 需要删除的角色ID
     * @return 结果
     */
    public int delInfo(String[] userId);
}
