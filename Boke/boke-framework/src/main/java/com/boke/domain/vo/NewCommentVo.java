package com.boke.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author DreamRay
 * @date 2023/8/28 16:43
 * @mood happy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCommentVo {
    /**
     * 评论id
     */
    private Integer id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 评论内容
     */
    private String commentContent;
    /**
     * 评论时间
     */
    private Date createTime;
}
