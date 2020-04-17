package com.ruoyi.project.user.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.SysNotice;
import com.ruoyi.project.system.service.ISysNoticeService;
import com.ruoyi.project.user.domain.UserInfo;
import com.ruoyi.project.user.service.IUserInfoService;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/info")
public class UserInfoController extends BaseController {
    @Autowired
    private IUserInfoService iUserInfoService;
    /**
     * 获取用户档案数据
     */@Autowired
    private ISysNoticeService noticeService;

    /**
     * 获取通知公告列表
     */

    @GetMapping("/notice")
    public TableDataInfo list(SysNotice notice)
    {
        startPage();
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

   @GetMapping(value = {"/","/user/{userId}"})
    public AjaxResult getUserInfo(@PathVariable(value = "userId", required = false) String userId){
       AjaxResult ajx = AjaxResult.success();
       if(iUserInfoService.selectUserInfo(userId) == null){
           UserInfo userInfo =new UserInfo();
           userInfo.setUserId(userId);
           userInfo.setSchool("广西科技大学鹿山学院");
           iUserInfoService.addUserInfo(userInfo);
       }
       ajx.put(AjaxResult.DATA_TAG,iUserInfoService.selectUserInfo(userId));
       return ajx;
   }

    /**
     * 用户编辑档案数据
     */
    @PostMapping("/edit")
    public AjaxResult editUserInfo(@RequestBody UserInfo userInfo){
            iUserInfoService.updateUserInfo(userInfo);
            return AjaxResult.success("更新信息成功");
    }
}
