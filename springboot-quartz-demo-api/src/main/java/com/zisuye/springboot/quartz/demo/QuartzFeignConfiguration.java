package com.zisuye.springboot.quartz.demo;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients
@ComponentScan
public class QuartzFeignConfiguration {

  public static final String SERVICE_NAME = "springboot-quartz-demo";
}
