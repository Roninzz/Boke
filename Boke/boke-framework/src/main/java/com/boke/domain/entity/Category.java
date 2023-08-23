package com.boke.domain.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (Category)表实体类
 *
 * @author DreamRay
 * @since 2023-08-13 15:17:00
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_category")
public class Category{
    @TableId
    //分类id
    private Integer id;

    //分类名    
     private String categoryName;
    //父分类id,如果没有则为-1    
     private Long pid;
    //创建时间    
     private Date createTime;
    //更新时间    
     private Date updateTime;
    //状态（0:正常,1:禁用）    
     private Integer status;

}

