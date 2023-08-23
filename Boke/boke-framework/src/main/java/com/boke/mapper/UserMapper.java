package com.boke.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boke.domain.entity.User;
import org.springframework.stereotype.Service;


/**
 * (User)表数据库访问层
 *
 * @author DreamRay
 * @since 2023-08-21 09:12:49
 */
@Service
public interface UserMapper extends BaseMapper<User> {

}

