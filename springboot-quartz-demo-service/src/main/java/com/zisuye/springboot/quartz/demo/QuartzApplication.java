package com.zisuye.springboot.quartz.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class QuartzApplication {

  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(QuartzApplication.class);
    application.run(args);
  }
}
