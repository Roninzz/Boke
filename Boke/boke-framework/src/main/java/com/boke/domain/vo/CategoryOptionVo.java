package com.boke.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DreamRay
 * @date 2023/8/18 11:50
 * @mood happy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryOptionVo {
    //分类id
    private Integer id;
    //分类名
    private String categoryName;
}
