package com.cheguo.niudun.console.server.config;

import com.cheguo.apollo.sys.biz.mq.UserCenterConsumer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

@Configuration
@ConditionalOnProperty(
        value = {"cheguo.apollo-sys.sso-mode"},
        matchIfMissing = true)
public class JMSConfig {

    @Value("${cheguo.apollo-sys.mq.url}")
    private String mqUrl;

    /**
     * 创建ActiveMQ连接工厂
     */
    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(mqUrl);
    }

    /**
     * 创建JMS消息处理模板
     */
    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(ActiveMQConnectionFactory connectionFactory) {
        return new JmsMessagingTemplate(connectionFactory);
    }

    /**
     * 创建队列的监听容器工厂
     */
    @Bean(name = "jmsListenerContainerQueue")
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }


    @Bean(name = "jmsListenerContainerTopic")
    public DefaultJmsListenerContainerFactory JmsListenerContainerFactory(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        //factory.setSessionTransacted(true); //事务设置
        //factory.setConcurrency("3-10");     //并发量接收
        //factory.setSessionAcknowledgeMode(4);
        return factory;
    }


    @Bean(name = "userCenterConsumer")
    public UserCenterConsumer createUserCenterConsumer() {
        return new UserCenterConsumer();
    }

}
