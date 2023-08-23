package com.boke.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author DreamRay
 * @date 2023/8/23 15:59
 * @mood happy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentChildVo {
    private Integer id;
    //父评论id
    private Integer parentId;
    //评论内容
    private String commentContent;
    //评论用户id
    private Integer fromUid;
    //评论用户昵称
    private String fromNickname;
    //评论用户头像
    private String avatar;
    //回复用户id
    private Integer toUid;
    //回复用户昵称
    private String toNickname;
    //评论时间
    private Date createTime;
}
