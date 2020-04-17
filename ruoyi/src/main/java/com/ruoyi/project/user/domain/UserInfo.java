package com.ruoyi.project.user.domain;


import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserInfo
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private String userId;

    /** 学号 */
    private String stu_no;

    /** 姓名 */
    private String userName;

    /** 身份证号码 */
    private String idNumber;

    /** 民族 */
    private String nation;

    /** 所在学校 */
    private String school;

    /** 入学时间 */
    private Date startTime;

    /** 政治面貌 */
    private String political;

    public UserInfo() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStu_no() {
        return stu_no;
    }

    public void setStu_no(String stu_no) {
        this.stu_no = stu_no;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("stu_no", getStu_no())
                .append("userName", getUserName())
                .append("idNumber", getIdNumber())
                .append("nation", getNation())
                .append("school", getSchool())
                .append("startTime", getStu_no())
                .append("political", getPolitical())
                .toString();
    }

}
