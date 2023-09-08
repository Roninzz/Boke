package com.boke.job;

import com.boke.constants.RedisConstant;
import com.boke.domain.entity.Article;
import com.boke.service.ArticleService;
import com.boke.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author DreamRay
 * @date 2023/9/7 15:48
 * @mood happy
 */
@Component
public class TimingJob {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ArticleService articleService;
    @Scheduled(cron = "0/600 * * * * ?")
    public void updateViewCount(){
        //获取redis中的浏览量
        Map<String, Integer> cacheMap = redisCache.getCacheMap(RedisConstant.ARTICLE_VIEW_COUNT);

        List<Article> articles = cacheMap.entrySet()
                .stream()
                .map(entry -> new Article(Integer.valueOf(entry.getKey()), entry.getValue().longValue()))
                .collect(Collectors.toList());
        //更新到数据库中
        articleService.updateBatchById(articles);
    }
}
