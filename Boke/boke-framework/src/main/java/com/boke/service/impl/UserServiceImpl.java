package com.boke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boke.domain.ResponseResult;
import com.boke.domain.entity.User;
import com.boke.domain.vo.UserVo;
import com.boke.mapper.UserMapper;
import com.boke.service.UserService;
import com.boke.utils.BeanCopyUtils;
import com.boke.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        @Autowired
        private PasswordEncoder passwordEncoder;

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

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult register(User user) {
        //对数据进行非空判断
        //对数据进行是否存在的判断
        //对密码进行加密处理
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        //数据存储到数据库
        save(user);
        return ResponseResult.okResult();
    }
}

