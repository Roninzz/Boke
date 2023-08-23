package com.boke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boke.domain.ResponseResult;
import com.boke.domain.entity.Tag;


/**
 * (Tag)表服务接口
 *
 * @author DreamRay
 * @since 2023-08-18 10:45:56
 */
public interface TagService extends IService<Tag> {

    ResponseResult getListTag();
}

