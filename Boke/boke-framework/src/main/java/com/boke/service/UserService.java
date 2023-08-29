package com.boke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boke.domain.ResponseResult;
import com.boke.domain.entity.User;


/**
 * (User)表服务接口
 *
 * @author DreamRay
 * @since 2023-08-23 15:37:24
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);
}

