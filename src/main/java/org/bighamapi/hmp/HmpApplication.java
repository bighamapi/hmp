package org.bighamapi.hmp;

import org.bighamapi.hmp.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(HmpApplication.class, args);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
