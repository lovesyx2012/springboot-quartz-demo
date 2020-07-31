package com.zisuye.springboot.quartz.demo.configurer;


import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.quartz.Scheduler;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


@Configuration
public class QuartzSchedulerConfig {
  @Bean(name = "SchedulerFactory")
  public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource)
      throws IOException {
    SchedulerFactoryBean factory = new SchedulerFactoryBean();
    factory.setQuartzProperties(quartzProperties());
    factory.setDataSource(dataSource);
    return factory;
  }

  @Bean
  public Properties quartzProperties() throws IOException {
    PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
    propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
    //在quartz.properties中的属性被读取并注入后再初始化对象
    propertiesFactoryBean.afterPropertiesSet();
    return propertiesFactoryBean.getObject();
  }

  /*
   * 通过SchedulerFactoryBean获取Scheduler的实例
   */
  @Bean(name = "Scheduler")
  public Scheduler scheduler(DataSource dataSource) throws IOException {
    Scheduler scheduler = schedulerFactoryBean(dataSource).getScheduler();
    return scheduler;
  }
}