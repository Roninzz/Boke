package com.boke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boke.constants.RedisConstant;
import com.boke.domain.ResponseResult;
import com.boke.domain.entity.SiteConfig;
import com.boke.mapper.SiteConfigMapper;
import com.boke.service.SiteConfigService;
import com.boke.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * (SiteConfig)表服务实现类
 *
 * @author DreamRay
 * @since 2023-09-01 16:07:58
 */
@Service("siteConfigService")
public class SiteConfigServiceImpl extends ServiceImpl<SiteConfigMapper, SiteConfig> implements SiteConfigService {
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private SiteConfigService siteConfigService;
    @Override
    public SiteConfig getSiteConfig() {
        //去redis中查找键为"site_setting"的数据
        SiteConfig siteConfig = redisCache.getCacheObject(RedisConstant.SITE_SETTING);
        //找不到则在数据库中找并缓存到redis中
        if (Objects.isNull(siteConfig)){
            //从数据库中加载
            siteConfig = siteConfigService.getById(1);
            redisCache.setCacheObject(RedisConstant.SITE_SETTING,siteConfig);

        }
        return siteConfig;
    }
}

