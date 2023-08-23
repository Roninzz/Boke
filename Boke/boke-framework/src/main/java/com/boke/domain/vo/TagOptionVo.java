package com.boke.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DreamRay
 * @date 2023/8/18 11:51
 * @mood happy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagOptionVo {
    /**
     * 标签id
     */
    @ApiModelProperty(value = "标签id")
    private Integer id;

    /**
     * 标签名
     */
    @ApiModelProperty(value = "标签名")
    private String tagName;
}
