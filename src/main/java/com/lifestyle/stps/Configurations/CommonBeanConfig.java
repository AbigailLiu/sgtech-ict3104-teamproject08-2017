package com.lifestyle.stps.Configurations;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Created by User 1 on 26/9/2017.
 */

@Configuration
public class CommonBeanConfig {
    @Bean
    public StrongPasswordEncryptor strongEncryptor(){
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        return encryptor;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(50);
        return executor;
    }
}
