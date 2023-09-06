package com.boke.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boke.domain.entity.Category;
import org.springframework.stereotype.Service;


/**
 * (Category)表数据库访问层
 *
 * @author DreamRay
 * @since 2023-08-13 12:06:14
 */
@Service
public interface CategoryMapper extends BaseMapper<Category> {

}

