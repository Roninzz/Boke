package com.boke.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boke.domain.entity.Tag;
import com.boke.domain.vo.TagVo;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * (Tag)表数据库访问层
 *
 * @author DreamRay
 * @since 2023-08-18 10:36:21
 */
@Repository
public interface TagMapper extends BaseMapper<Tag> {
    List<TagVo> selectTagVoList();
}

