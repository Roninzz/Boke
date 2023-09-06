package com.boke.controller;

import com.boke.domain.ResponseResult;
import com.boke.domain.vo.BlogInfoVO;
import com.boke.service.BlogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DreamRay
 * @date 2023/9/1 16:42
 * @mood 博客控制器
 */
@RestController
public class BlogInfoController {
    @Autowired
    private BlogInfoService blogInfoService;

    /**
     * 查看博客信息
     */
    @GetMapping("/")
    public ResponseResult<BlogInfoVO> getBlogInfo() {
        return ResponseResult.okResult(blogInfoService.getBlogInfo());
    }
}
