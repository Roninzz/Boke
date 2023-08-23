package com.boke.controller;

import com.boke.domain.ResponseResult;
import com.boke.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DreamRay
 * @date 2023/8/14 15:11
 * @mood 友链
 */

@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @GetMapping("/getAllFriend")
    public ResponseResult getAllFriend(){
        return friendService.getAllFriend();
    }
}
