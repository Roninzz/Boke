package com.boke.domain.entity;

import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (Comment)表实体类
 *
 * @author DreamRay
 * @since 2023-08-23 14:15:13
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_comment")
public class Comment{
    //评论id
    @TableId
    private Integer id;

    //类型 (1文章 2友链 3说说)    
     private Integer commentType;
    //类型id (类型为友链则没有值)    
     private Integer typeId;
    //父评论id    
     private Integer parentId;
    //回复评论id    
     private Integer replyId;
    //评论内容    
     private String commentContent;
    //评论用户id    
     private Integer fromUid;
    //回复用户id    
     private Integer toUid;
    //是否通过 (0否 1是)    
     private Integer isCheck;

     @TableField(fill = FieldFill.INSERT)
    //评论时间    
     private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    //更新时间    
     private Date updateTime;
}

