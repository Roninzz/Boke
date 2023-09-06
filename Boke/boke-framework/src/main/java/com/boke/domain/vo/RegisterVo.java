package com.boke.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author DreamRay
 * @date 2023/9/1 18:26
 * @mood 用户注册信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterVo {
    /**
     * 用户名
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码不能少于6位")
    private String password;
    /**
     * 验证码
     */
    private String code;
}
