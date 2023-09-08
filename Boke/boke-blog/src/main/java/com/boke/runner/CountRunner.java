package com.boke.runner;

import com.boke.constants.RedisConstant;
import com.boke.domain.entity.Article;
import com.boke.mapper.ArticleMapper;
import com.boke.utils.RedisCache;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author DreamRay
 * @date 2023/9/7 11:15
 * @mood happy
 */
@Component
public class CountRunner implements CommandLineRunner{
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisCache redisCache;
    @Override
    public void run(String... args) throws Exception {
        //查詢博客信息 id viewCount
        List<Article> articles = articleMapper.selectList(null);
        //封装为Map
        Map<String, Integer> viewCountMap = articles.stream()
                .collect(Collectors.toMap(article -> article.getId().toString(), (Article a) -> a.getViewCount().intValue()));
        //存储到redis中
        redisCache.setCacheMap(RedisConstant.ARTICLE_VIEW_COUNT,viewCountMap);
    }
}
