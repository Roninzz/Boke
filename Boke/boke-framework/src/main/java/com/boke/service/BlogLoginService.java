package com.boke.service;

import com.boke.domain.ResponseResult;
import com.boke.domain.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author DreamRay
 * @date 2023/8/21 10:56
 * @mood happy
 */

public interface BlogLoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}
