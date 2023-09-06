package com.boke.controller;

import com.boke.domain.ResponseResult;
import com.boke.domain.entity.User;
import com.boke.domain.vo.RegisterVo;
import com.boke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author DreamRay
 * @date 2023/8/24 11:32
 * @mood happy
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    //获取登录用户信息
    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    //更新用户信息
    @PutMapping("/userInfo")
    public ResponseResult updateUserInfo(@RequestBody User user){
        return userService.updateUserInfo(user);
    }
    //注册
    @PostMapping("/register")
    public ResponseResult register(@Validated @RequestBody RegisterVo registerVo){
        return userService.register(registerVo);
    }
}
