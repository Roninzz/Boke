package com.boke.domain.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (Tag)表实体类
 *
 * @author DreamRay
 * @since 2023-08-18 10:36:47
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_tag")
public class Tag{
    //标签id
    @TableId
    private Integer id;

    //标签名    
     private String tagName;
    //创建时间    
     private Date createTime;
    //更新时间    
     private Date updateTime;
}

