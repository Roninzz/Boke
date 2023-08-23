package com.boke.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author DreamRay
 * @date 2023/8/14 11:46
 * @mood happy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVo {
    //文章id
    private Integer id;
    //作者id
    private Integer userId;
    //分类id
    private Integer categoryId;
    //分类名称
    private String categoryName;
    //文章标题
    private List<TagOptionVo> tagVOList;
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
    //评论数
    private Integer commentCount;
    //访问量
    private Long viewCount;
    //点赞量
    private Integer likeCount;
    //上一篇文章
    private ArticlePaginationVo lastArticle;
    //下一篇文章
    private ArticlePaginationVo nextArticle;
    //发表时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}
