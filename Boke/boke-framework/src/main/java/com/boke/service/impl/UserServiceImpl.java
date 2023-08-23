package com.boke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boke.domain.entity.User;
import com.boke.mapper.UserMapper;
import com.boke.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author DreamRay
 * @since 2023-08-23 15:37:24
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

