package com.boke.domain.vo;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author DreamRay
 * @date 2023/8/21 15:57
 * @mood happy
 */
@Data
@Accessors(chain = true)
public class UserInfoVo {
    /*
    * 主键
    * */
    private Integer id;
    /*
     * 昵称
     * */
    private String nickname;
    /*
     * 头像
     * */
    private String avatar;
    /*
     * 邮箱
     * */
    private String email;

}
