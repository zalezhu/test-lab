package com.zale.tools.clawer.web.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 *
 * @Author Zale
 * @Date 2017/4/16 下午10:06
 *
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        RabbitAutoConfiguration.class
})
@EnableScheduling
@ComponentScan({ "com.zale" })
@EnableTransactionManagement
@EnableMongoRepositories(basePackages = "com.zale.tools.repository.mongo")
public class ClawerWebStarter {
    public static void main(String[] args) {
        SpringApplication.run(ClawerWebStarter.class, args);
    }
}
