package com.boke.domain.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (Friend)表实体类
 *
 * @author DreamRay
 * @since 2023-08-14 15:26:12
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_friend")
public class Friend{
    //友链id
    @TableId
    private Integer id;

    //友链名称    
     private String name;
    //友链颜色    
     private String color;
    //友链头像    
     private String avatar;
    //友链地址    
     private String url;
    //友链介绍    
     private String introduction;
    //创建时间    
     private Date createTime;
    //更新时间    
     private Date updateTime;
}

