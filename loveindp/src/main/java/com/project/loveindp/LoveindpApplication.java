package com.project.loveindp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = {"com.project.loveindp"})
@MapperScan("com.project.loveindp.dal")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class LoveindpApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoveindpApplication.class, args);
    }

}
