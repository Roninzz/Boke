package com.boke.service;

import com.boke.domain.vo.BlogInfoVO;
import org.springframework.stereotype.Service;

/**
 * @author DreamRay
 * @date 2023/9/1 16:47
 * @mood 博客业务接口
 */
@Service
public interface BlogInfoService {
    /**
     * 上传访客信息
     */
//    void report();
    /**
     * 查看博客信息
     *
     * @return 博客信息
     */
    BlogInfoVO getBlogInfo();

    /**
     * 查看博客后台信息
     *
     * @return 博客后台信息
     */
//    BlogBackInfoVO getBlogBackInfo();

    /**
     * 查看关于我信息
     *
     * @return 关于我信息
     */
//    String getAbout();
}
