package com.cheguo.niudun.console.server.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.validation.Validator;

/**
 * 模块名
 *
 * @author spy
 * @version 1.0 2018/3/15
 * @since 1.0
 */
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public Validator validator(MessageSource messageSource) {
        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
        factory.setValidationMessageSource(messageSource);
        return factory;
//        HibernateValidatorConfiguration configuration = (HibernateValidatorConfiguration) Validation.byProvider(HibernateValidator.class).configure();
//        configuration.failFast(true).ignoreXmlConfiguration();
//        MessageInterpolator defaultInterpolator = configuration.getDefaultMessageInterpolator();
//        configuration.messageInterpolator(new LocaleSpecifiedMessageInterpolator(defaultInterpolator, Locale.CHINESE));
//        return configuration.buildValidatorFactory().getValidator();
    }


//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
//        resolver.setOneIndexedParameters(true);
//        resolver.setFallbackPageable(new PageRequest(1, 10));
//        argumentResolvers.add(resolver);
//        super.addArgumentResolvers(argumentResolvers);
//    }

    @Bean
    public HandlerInterceptor getLocaleChangeInterceptor() {
        return new LocaleChangeInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(getLocaleChangeInterceptor());
    }
}
