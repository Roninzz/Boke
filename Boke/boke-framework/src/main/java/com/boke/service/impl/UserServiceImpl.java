package com.boke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boke.domain.ResponseResult;
import com.boke.domain.entity.User;
import com.boke.domain.vo.UserVo;
import com.boke.mapper.UserMapper;
import com.boke.service.UserService;
import com.boke.utils.BeanCopyUtils;
import com.boke.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author DreamRay
 * @since 2023-08-23 15:37:24
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserService userService;

    @Override
    public ResponseResult userInfo() {
        //获取当前用户id
        Integer userId = SecurityUtils.getUserId();
        //根据用户id查询用户信息
        User user = userService.getById(userId);
        //封装成UserInfoVo
        UserVo userVo = BeanCopyUtils.copyBean(user, UserVo.class);
        return ResponseResult.okResult(userVo);
    }
}

