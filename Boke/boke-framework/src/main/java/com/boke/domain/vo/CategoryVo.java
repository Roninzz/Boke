package com.boke.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo {
    //分类id
    private Integer id;
    //分类名
    private String categoryName;
    //文章数量
    private Long articleCount;
}
