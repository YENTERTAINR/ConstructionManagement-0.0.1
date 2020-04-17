package com.ruoyi.project.user.service.impl;

import com.ruoyi.project.user.domain.UserInfo;
import com.ruoyi.project.user.mapper.UserInfoMapper;
import com.ruoyi.project.user.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IUserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public UserInfo selectUserInfo(String userId) {
        return userInfoMapper.getInfo(userId);
    }

    @Override
    public boolean addUserInfo(UserInfo userInfo) {
        boolean flag=false;
        if(userInfoMapper.addInfo(userInfo)==1) flag=true;
        return flag;
    }

    @Override
    public boolean updateUserInfo(UserInfo userInfo) {
        boolean flag=false;
        if(userInfoMapper.upodateInfo(userInfo)==1) flag=true;
        return flag;
    }
}
