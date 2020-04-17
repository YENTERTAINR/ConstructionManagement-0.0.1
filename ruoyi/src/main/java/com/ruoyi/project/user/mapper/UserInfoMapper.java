package com.ruoyi.project.user.mapper;

import com.ruoyi.project.user.domain.UserInfo;

public interface UserInfoMapper {
    /**
     *
     * @param userId 用户ID
     * @return 获取个人档案信息
     */
    public UserInfo getInfo(String userId);

    /**
     *
     * @param userInfo 用户ID参照其他表
     * @return 添加用户档案
     */
    public int addInfo(UserInfo userInfo);

    /**
     *
     * @param userInfo 用户档案信息
     * @return 修改用户档案
     */
    public int upodateInfo(UserInfo userInfo);
}
