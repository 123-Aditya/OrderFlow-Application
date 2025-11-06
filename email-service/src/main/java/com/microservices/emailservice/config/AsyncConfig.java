package com.microservices.emailservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableAsync
@EnableRetry
public class AsyncConfig {

    @Bean(name = "emailExecutor")
    public Executor emailExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);   // minimum threads
        executor.setMaxPoolSize(6);    // max threads
        executor.setQueueCapacity(50); // queued tasks before new threads spawn
        executor.setThreadNamePrefix("EmailSender-");
        executor.initialize();
        return executor;
    }
}
