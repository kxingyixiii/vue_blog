package com.kxingyi.blog.config;

import com.kxingyi.blog.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用于将一些实体类放入Spring容器
 *
 * @Author: 杨德石
 * @Date: 2020/2/9 14:37
 * @Version 1.0
 */
@Configuration
public class BeanConfig {

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

}
