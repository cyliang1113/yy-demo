package com.cheguo.niudun.console.server.config;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2018/3/16
 * @since 1.0
 */
@Slf4j
@Configuration
@MapperScan({"com.cheguo.apollo.sys.biz.dao","com.cheguo.niudun.console.server.biz.dao"})
@AutoConfigureAfter(DataSourceConfig.class)
public class MybatisConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
