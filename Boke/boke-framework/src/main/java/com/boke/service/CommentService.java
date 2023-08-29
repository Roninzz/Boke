package com.boke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boke.domain.ResponseResult;
import com.boke.domain.entity.Comment;


/**
 * (Comment)表服务接口
 *
 * @author DreamRay
 * @since 2023-08-23 14:14:32
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(Integer commentType, Integer typeId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);

    ResponseResult newComment();
}

