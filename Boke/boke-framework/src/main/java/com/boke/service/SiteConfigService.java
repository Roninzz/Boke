package com.boke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boke.domain.ResponseResult;
import com.boke.domain.entity.SiteConfig;


/**
 * (SiteConfig)表服务接口
 *
 * @author DreamRay
 * @since 2023-09-01 16:07:58
 */
public interface SiteConfigService extends IService<SiteConfig> {

    SiteConfig getSiteConfig();
}

