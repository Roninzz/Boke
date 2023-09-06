package com.boke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.boke.constants.RedisConstant;
import com.boke.constants.SystemConstants;
import com.boke.domain.entity.Article;
import com.boke.domain.entity.SiteConfig;
import com.boke.domain.vo.BlogInfoVO;
import com.boke.mapper.ArticleMapper;
import com.boke.mapper.CategoryMapper;
import com.boke.mapper.TagMapper;
import com.boke.service.BlogInfoService;
import com.boke.service.SiteConfigService;
import com.boke.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author DreamRay
 * @date 2023/9/1 16:50
 * @mood 博客业务接口实现类
 */
@Service
public class BlogInfoServiceImpl implements BlogInfoService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private SiteConfigService siteConfigService;
//    @Override
//    public void report() {
//
//    }

    @Override
    public BlogInfoVO getBlogInfo() {
        //文章数量

        LambdaQueryWrapper<Article> lqwArticle = new LambdaQueryWrapper<>();
        lqwArticle.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_PUBLIC)
                .eq(Article::getIsDelete,SystemConstants.IS_TOP_FALSE);
        Integer articleCount = articleMapper.selectCount(lqwArticle);
        //分类数量
        Integer categoryCount = categoryMapper.selectCount(null);
        //标签数量
        Integer tagCount = tagMapper.selectCount(null);
        //博客访问量
        Integer count = redisCache.getCacheObject(RedisConstant.BLOG_VIEW_COUNT);
        //判断是否为空，如果为空就变为0返回
        String viewCount = Optional.ofNullable(count).orElse(0).toString();
        //网站配置
        SiteConfig siteConfig = siteConfigService.getSiteConfig();
        return BlogInfoVO.builder()
                .articleCount(articleCount)
                .categoryCount(categoryCount)
                .tagCount(tagCount)
                .viewCount(viewCount)
                .siteConfig(siteConfig)
                .build();
    }

//    @Override
//    public String getAbout() {
//        return null;
//    }
}
