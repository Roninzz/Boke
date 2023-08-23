package com.boke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boke.domain.ResponseResult;
import com.boke.domain.entity.Tag;
import com.boke.domain.vo.TagVo;
import com.boke.mapper.TagMapper;
import com.boke.service.TagService;
import com.boke.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Tag)表服务实现类
 *
 * @author DreamRay
 * @since 2023-08-18 10:45:56
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper ;
    @Override
    public ResponseResult getListTag() {
        //找到所有文章标签
        List<TagVo> tags = tagMapper.selectTagVoList();
        //封装Vo

        return ResponseResult.okResult(tags);
    }
}

