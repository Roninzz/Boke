package com.boke.controller;

import com.boke.domain.ResponseResult;
import com.boke.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DreamRay
 * @date 2023/8/18 10:40
 * @mood happy
 */

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 查看标签列表
     *
     * @return {@link ResponseResult<>} 标签列表
     */
    @GetMapping("/list")
    public ResponseResult listTag(){
        return tagService.getListTag();
    }
}
