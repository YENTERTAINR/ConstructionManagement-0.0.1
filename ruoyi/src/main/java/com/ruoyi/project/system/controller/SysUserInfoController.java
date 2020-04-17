package com.ruoyi.project.system.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.service.ISysUserInfoService;
import com.ruoyi.project.user.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/userManage")
public class SysUserInfoController extends BaseController {
    @Autowired
    private ISysUserInfoService iSysUserInfoService;

    /**
     * 获取用户档案列表
     */
    @GetMapping("/list")
    public AjaxResult getList(){
        List<UserInfo> list = iSysUserInfoService.selectInfoList();
        return AjaxResult.success(list);
    }

    /**
     * 根据ID查询用户档案
     */
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfobyId(@PathVariable String userId){
        return AjaxResult.success(iSysUserInfoService.selectInfoById(userId));
    }

    /**
     * 删除档案信息
     */
    @DeleteMapping("/{userIds}")
    public AjaxResult delInfo(@PathVariable String[] userIds){
        return toAjax(iSysUserInfoService.delInfo(userIds));
    }
}
