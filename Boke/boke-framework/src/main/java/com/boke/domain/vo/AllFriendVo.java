package com.boke.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author DreamRay
 * @date 2023/8/14 16:21
 * @mood happy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllFriendVo {
    //友链id
    private Integer id;
    //友链名称
    private String name;
    //友链颜色
    private String color;
    //友链头像
    private String avatar;
    //友链地址
    private String url;
    //友链介绍
    private String introduction;
}
