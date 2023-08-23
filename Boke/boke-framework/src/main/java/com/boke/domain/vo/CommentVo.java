package com.boke.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author DreamRay
 * @date 2023/8/23 15:02
 * @mood happy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {
    private Integer id;
    //父评论id
//    private Integer parentId;
    //回复评论id
//    private Integer replyId;
    //评论内容
    private String commentContent;
    //评论用户id
    private Integer fromUid;
    //评论用户昵称
    private String fromNickname;
    //评论用户头像
    private String avatar;
    //回复用户id
//    private Integer toUid;
    //评论时间
    private Date createTime;
    //子评论集合
    private List<CommentChildVo> childList;
    //子评论数量
    private Integer childCount;

}
