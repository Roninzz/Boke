package com.boke.controller;

import com.boke.domain.ResponseResult;
import com.boke.domain.entity.Comment;
import com.boke.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author DreamRay
 * @date 2023/8/23 14:32
 * @mood happy
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    //获取评论
    @GetMapping("/commentList")
    public ResponseResult commentList(Integer commentType,Integer typeId,Integer pageNum,Integer pageSize){
        return commentService.commentList(commentType,typeId,pageNum,pageSize);
    }
    //发表评论
    @PostMapping
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
}
