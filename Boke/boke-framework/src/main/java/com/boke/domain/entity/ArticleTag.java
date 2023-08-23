package com.boke.domain.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (ArticleTag)表实体类
 *
 * @author DreamRay
 * @since 2023-08-18 11:05:05
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_article_tag")
public class ArticleTag{
    //主键
    @TableId
    private Integer id;

    //文章id    
     private Integer articleId;
    //标签id    
     private Integer tagId;
}

