package com.xshop.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author: xuhao
 *  初始化类
 * @Date：Created on 2018/9/5 15:16.
 */
@Configuration
public class BeanConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }



    @Bean( name = "myScheduledThreadPoolExecutor")
    public ScheduledThreadPoolExecutor ScheduledThreadPoolExecutorConfig(){
        return new ScheduledThreadPoolExecutor(10) ;
    }}
