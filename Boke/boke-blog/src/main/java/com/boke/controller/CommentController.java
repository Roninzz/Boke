package com.boke.controller;

import com.boke.domain.ResponseResult;
import com.boke.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/commentList")
    public ResponseResult commentList(Integer commentType,Integer typeId,Integer pageNum,Integer pageSize){
        return commentService.commentList(commentType,typeId,pageNum,pageSize);
    }
}
