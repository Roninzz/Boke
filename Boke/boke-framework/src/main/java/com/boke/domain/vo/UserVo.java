package com.boke.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author DreamRay
 * @date 2023/8/24 11:48
 * @mood happy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private Integer id;
    //用户昵称
    private String nickname;
    //用户名
    private String username;
    //用户密码
//    private String password;
    //头像
    private String avatar;
    //个人网站
    private String webSite;
    //个人简介
    private String intro;
    //邮箱
    private String email;
    //登录ip
//    private String ipAddress;
    //登录地址
//    private String ipSource;
    //登录方式 (1邮箱 2QQ 3Gitee 4Github)
//    private Integer loginType;
    //是否禁用 (0否 1是)
//    private Integer isDisable;
    //登录时间
//    private Date loginTime;
    //创建时间
//    private Date createTime;
    //更新时间
//    private Date updateTime;
}
