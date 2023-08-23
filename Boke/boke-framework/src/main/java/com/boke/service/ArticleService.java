package com.boke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boke.domain.ResponseResult;
import com.boke.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    //热门文章查询
    ResponseResult getHotArticleList();
    //查询所有文章
    ResponseResult getArticleList(Integer pageName, Integer pageSize, Long categoryId,Long tagId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult getArchivesList(Integer pageNum, Integer pageSize, Long categoryId);
}
