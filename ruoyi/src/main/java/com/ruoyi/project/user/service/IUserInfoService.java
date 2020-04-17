package com.ruoyi.project.user.service;

import com.ruoyi.project.user.domain.UserInfo;

public interface IUserInfoService {
    /**
     * 查询档案信息
     *
     * @param userId 用户ID
     * @return 用户档案信息
     */
    public UserInfo selectUserInfo(String userId);

    /**
     * 添加档案信息
     *
     * @param userInfo 用户档案
     * @return 是否更改成功
     */
    public boolean addUserInfo(UserInfo userInfo);

    /**
     * 修改档案信息
     *
     * @param userInfo 用户档案
     * @return 是否更改成功
     */
    public boolean updateUserInfo(UserInfo userInfo);
}
