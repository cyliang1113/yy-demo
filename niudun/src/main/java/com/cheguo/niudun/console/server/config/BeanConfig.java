
package com.cheguo.niudun.console.server.config;



import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.util.IntrospectorCleanupListener;

import java.util.Locale;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2018/4/25
 * @since 1.0
 */
@Component
@Slf4j
public class BeanConfig {

    /**
     * 注入FastJSON
     *
     * @return
     */
    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.QuoteFieldNames,
                SerializerFeature.IgnoreErrorGetter
        );
        //
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return fastConverter;
    }


    @Bean
    public ServletListenerRegistrationBean customListener() {
        ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean();

        IntrospectorCleanupListener customListener = new IntrospectorCleanupListener();
        registrationBean.setListener(customListener);
        registrationBean.setOrder(1);

        return registrationBean;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    public CookieLocaleResolver localResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.CHINA);

        return resolver;
    }

    @Value("${cheguo.apollo-console-server.task-core-pool-size:10}")
    private Integer taskCorePoolSize;

    @Value("${cheguo.apollo-console-server.task-max-pool-size:200}")
    private Integer taskMaxPoolSize;

    @Value("${cheguo.apollo-console-server.task-queue-capacity:100}")
    private Integer taskQueueCapacity;

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(taskCorePoolSize);
        executor.setMaxPoolSize(taskMaxPoolSize);
        executor.setQueueCapacity(taskQueueCapacity);
        executor.setThreadNamePrefix("apollo-console-server-TaskExecutor-");

//        executor.initialize();
        return executor;
    }

    @Value("${cheguo.apollo-console-server.async-task-concurrency-limit:100}")
    private Integer asyncTaskConcurrencyLimit;

    @Bean
    public SimpleAsyncTaskExecutor simpleAsyncTaskExecutor() {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();

        executor.setConcurrencyLimit(asyncTaskConcurrencyLimit);
        executor.setThreadNamePrefix("apollo-console-server-AsyncTaskExecutor-");

        return executor;
    }
}
