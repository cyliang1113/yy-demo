
package com.cheguo.niudun.console.server;

import com.cheguo.apollo.sys.web.ApolloSysConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;



/**
 * Bootstrap
 *
 * @author spy
 * @version 1.0 2018/4/24
 * @since 1.0
 */
@Slf4j
@EnableAsync
@SpringBootApplication(
        exclude = {
                BatchAutoConfiguration.class,
                JdbcTemplateAutoConfiguration.class,
                DataSourceAutoConfiguration.class,
//                DataSourceTransactionManagerAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class,
                JpaRepositoriesAutoConfiguration.class
        }
)
@ComponentScans({
        @ComponentScan(basePackages = {
                "com.cheguo.niudun.console.server.controller.*"
        })
})
@EnableTransactionManagement
@EnableConfigurationProperties
@ImportResource(locations = {"classpath:spring/spring.xml"})
@Import(ApolloSysConfig.class)
public class NiudunApplication {

    public static void main(String[]args){
        SpringApplication.run(NiudunApplication.class,args);
    }
}
