package com.boke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boke.constants.RedisConstant;
import com.boke.constants.SystemConstants;
import com.boke.domain.ResponseResult;
import com.boke.domain.entity.SiteConfig;
import com.boke.domain.entity.User;
import com.boke.domain.vo.RegisterVo;
import com.boke.domain.vo.UserVo;
import com.boke.enums.LoginTypeEnum;
import com.boke.mapper.UserMapper;
import com.boke.service.UserService;
import com.boke.utils.BeanCopyUtils;
import com.boke.utils.RedisCache;
import com.boke.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Objects;

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
        @Autowired
        private UserMapper userMapper;
        @Autowired
        private RedisCache redisCache;

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
//        updateById(user);
        User newuser = User.builder()
                .id(SecurityUtils.getUserId())
                .nickname(user.getNickname())
                .intro(user.getIntro())
                .webSite(user.getWebSite())
                .build();
        userService.updateById(newuser);
        return ResponseResult.okResult();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult register(RegisterVo registerVo) {
        //对数据进行非空判断
        //对数据进行是否存在的判断
        //判断邮箱是否已经存在
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper();
        lqw.eq(!Objects.isNull(registerVo),User::getUsername,registerVo.getUsername());
        User user = userMapper.selectOne(lqw);
        Assert.isNull(user,"邮箱已被注册");
        //对密码进行加密处理
        String encodePassword = passwordEncoder.encode(registerVo.getPassword());
//        registerVo.setPassword(encodePassword);
        SiteConfig siteConfig = redisCache.getCacheObject(RedisConstant.SITE_SETTING);
        //添加用户
        User newUser = User.builder()
                .username(registerVo.getUsername())
                .email(registerVo.getUsername())
                .nickname(SystemConstants.USER_NICKNAME + IdWorker.getId())
                .avatar(siteConfig.getUserAvatar())
                .password(encodePassword)
                .loginType(LoginTypeEnum.EMAIL.getLoginType())
                .build();
        userMapper.insert(newUser);
        return ResponseResult.okResult();
    }
}

