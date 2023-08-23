package com.boke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boke.constants.SystemConstants;
import com.boke.domain.ResponseResult;
import com.boke.domain.entity.Article;
import com.boke.domain.entity.Category;
import com.boke.domain.vo.CategoryVo;
import com.boke.mapper.CategoryMapper;
import com.boke.service.ArticleService;
import com.boke.service.CategoryService;
import com.boke.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * (Category)表服务实现类
 *
 * @author DreamRay
 * @since 2023-08-13 12:06:16
 */
@Service("categoryService")
@Transactional
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    //查询所有分类
    @Override
    public ResponseResult getCategoryList() {
        //分步查询
        //查询文章表  状态为以发布的文章
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_PUBLIC);
        List<Article> articleList = articleService.list(articleWrapper);
        //获取这些文章的分类id，并且去重
        Set<Integer> categoryIds = articleList.stream()
                .map(Article::getCategoryId)
                .collect(Collectors.toSet());
        //通过分类id在分类表中查询  状态为正常的分类信息
        List<Category> categories = listByIds(categoryIds);
        categories.stream()
                //过滤掉文章分类状态为弃用的分类
                .filter(category -> SystemConstants.CATEGORY_STATE_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        //查询每个categoryId的文章数量
        categoryVos.stream()
                .map(categoryVo -> {
                        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
                        lqw.eq(Article::getCategoryId, categoryVo.getId());
                        long count = articleService.list(lqw).stream().count();
                        categoryVo.setArticleCount(count);
                        return categoryVo;
                })
                .collect(Collectors.toList());
        return ResponseResult.okResult(categoryVos);
    }


}

