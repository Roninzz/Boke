package com.boke.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DreamRay
 * @date 2023/8/18 18:25
 * @mood happy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePaginationVo {
    //文章id
    private Integer id;
    //文章标题
    private String articleTitle;
    //文章缩略图
    private String articleCover;
}
