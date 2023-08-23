package com.boke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boke.domain.ResponseResult;
import com.boke.domain.entity.Category;


/**
 * (Category)表服务接口
 *
 * @author DreamRay
 * @since 2023-08-13 12:06:16
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

