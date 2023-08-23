package com.boke.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListVo {
    //文章id
    private Integer id;
    //文章分类
    private Integer categoryId;
    //分类名称
    private String categoryName;
    //文章标签
    private List<TagOptionVo> tagVOList;
    //缩略图
    private String articleCover;
    //文章标题
    private String articleTitle;
    //文章摘要
    private String articleSummary;
    //文章内容
    private String articleContent;
    //是否置顶 (0否 1是）
    private Integer isTop;
    //评论数
//    private Integer commentCount;
    //发表时间
    private Date createTime;

}
