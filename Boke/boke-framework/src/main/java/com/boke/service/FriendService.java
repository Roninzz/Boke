package com.boke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boke.domain.ResponseResult;
import com.boke.domain.entity.Friend;


/**
 * (Friend)表服务接口
 *
 * @author DreamRay
 * @since 2023-08-14 15:21:38
 */
public interface FriendService extends IService<Friend> {

    ResponseResult getAllFriend();
}

