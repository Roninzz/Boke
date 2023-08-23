package com.boke.utils;

import com.boke.domain.entity.LoginUser;

import io.swagger.models.auth.In;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author DreamRay
 * @date 2023/8/23 18:02
 * @mood happy
 */
public class SecurityUtils {
    /*
    * 获取用户
    * */
    public static LoginUser getLoginUser()
    {
        return (LoginUser) getAuthentication().getPrincipal();
    }

    /*
    * 获取Authentication
    * */
    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin(){
        Integer id = getLoginUser().getUser().getId();
        return id != null && 1L == id;
    }

    public static Integer getUserId(){
        return getLoginUser().getUser().getId();
    }
}
