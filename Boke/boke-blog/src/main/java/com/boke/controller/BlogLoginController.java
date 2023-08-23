package com.boke.controller;

import com.boke.domain.ResponseResult;
import com.boke.domain.entity.User;
import com.boke.enums.AppHttpCodeEnum;
import com.boke.exception.SystemException;
import com.boke.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DreamRay
 * @date 2023/8/21 10:48
 * @mood 登录实现类
 */
@RestController
public class BlogLoginController {
    @Autowired
    private BlogLoginService blogLoginService;

    //登录
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        //校验
        if (!StringUtils.hasText(user.getUsername())){
            //提示  必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }
    //退出
    @PostMapping("/logout")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}
