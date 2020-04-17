package com.ruoyi.project.system.service.impl;

import com.ruoyi.project.system.mapper.SysUserInfoMapper;
import com.ruoyi.project.system.service.ISysUserInfoService;
import com.ruoyi.project.user.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysUserInfoServiceImpl implements ISysUserInfoService {
    @Autowired
    private SysUserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> selectInfoList() {
        return userInfoMapper.selectInfoList();
    }

    @Override
    public UserInfo selectInfoById(String userId) {
        return userInfoMapper.selectInfoById(userId);
    }

    @Override
    public int delInfo(String[] userIds) {
        return userInfoMapper.delInfo(userIds);
    }
}
