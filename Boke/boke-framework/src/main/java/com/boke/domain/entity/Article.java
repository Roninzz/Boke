package com.boke.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (Article)表实体类
 *
 * @author makejava
 * @since 2023-07-25 16:33:07
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_article")
@Accessors(chain = true)
public class Article{
    @TableId
    //文章id
    private Integer id;
    //作者id
    private Integer userId;
    //分类id
    private Integer categoryId;
    //分类名称
    @TableField(exist = false)
    private String categoryName;
    //缩略图
    private String articleCover;
    //文章标题
    private String articleTitle;
    //文章摘要
    private String articleSummary;
    //文章内容
    private String articleContent;
    //类型 (1原创 2转载 3翻译)
    private Integer articleType;
    //是否置顶 (0否 1是）
    private Integer isTop;
    //是否删除 (0否 1是)
    private Integer isDelete;
    //是否推荐 (0否 1是)
    private Integer isRecommend;
    //状态 (1公开 2私密 3评论可见)
    private Integer status;
    //评论数
    private Integer commentCount;
    //访问量
    private Long viewCount;
    //发表时间
    private Date createTime;
    //更新时间
    private Date updateTime;


    public Article(Integer id, long viewCount) {
        this.id = id;
        this.viewCount = viewCount;
    }
}

