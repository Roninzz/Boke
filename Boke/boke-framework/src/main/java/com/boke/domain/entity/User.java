package com.boke.domain.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (User)表实体类
 *
 * @author DreamRay
 * @since 2023-08-21 09:13:09
 */
@SuppressWarnings("serial")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
public class User{
    //用户id
    @TableId
    private Integer id;

    //用户昵称    
     private String nickname;
    //用户名    
     private String username;
    //用户密码    
     private String password;
    //头像    
     private String avatar;
    //个人网站    
     private String webSite;
    //个人简介    
     private String intro;
    //邮箱    
     private String email;
    //登录ip    
     private String ipAddress;
    //登录地址    
     private String ipSource;
    //登录方式 (1邮箱 2QQ 3Gitee 4Github)    
     private Integer loginType;
    //是否禁用 (0否 1是)    
     private Integer isDisable;
    //登录时间    
     private Date loginTime;
    //创建时间    
     private Date createTime;
    //更新时间    
     private Date updateTime;
}

