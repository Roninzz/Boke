package com.boke.controller;

import com.boke.domain.ResponseResult;
import com.boke.service.SiteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DreamRay
 * @date 2023/9/1 16:09
 * @mood happy
 */
@RestController
@RequestMapping("/admin")
public class SiteConfigController {
    @Autowired
    private SiteConfigService siteConfigService;

    public ResponseResult getSiteConfig(){
        return ResponseResult.okResult(siteConfigService.getSiteConfig());
    }
}
