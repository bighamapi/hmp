package org.bighamapi.hmp;

import org.bighamapi.hmp.dto.RSSFeedViewer;
import org.bighamapi.hmp.interceptor.HmpInterceptor;
import org.bighamapi.hmp.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableCaching             //开启缓存
public class HmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(HmpApplication.class, args);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
    @Bean
    public HmpInterceptor hmpInterceptor(){
        return new HmpInterceptor();
    }
    @Bean
    public RSSFeedViewer rssViewer(){
        return new RSSFeedViewer();
    }
}
