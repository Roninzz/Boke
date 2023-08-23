package com.boke.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author DreamRay
 * @date 2023/8/14 9:30
 * @mood page分页拦截器
 */

@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件。如果你不配置，分页插件将不生效
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 指定数据库方言为
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        //setOverflow(true)表示当请求的页码超过最大页数时，回到首页
        paginationInnerInterceptor.setOverflow(true);
        //setMaxLimit(500L)表示设置单页返回的最大记录数为500条
        paginationInnerInterceptor.setMaxLimit(500L);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }

}
