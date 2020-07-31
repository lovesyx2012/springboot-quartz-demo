package com.zisuye.springboot.quartz.demo.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanUtils implements ApplicationContextAware {

  private static ApplicationContext myApplicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    setMyApplicationContext(applicationContext);
  }

  //通过name获取 Bean.
  public static Object getBean(String name) {
    return getMyApplicationContext().getBean(name);
  }

  //通过class获取Bean.
  public static <T> T getBean(Class<T> clazz) {
    return getMyApplicationContext().getBean(clazz);
  }

  //通过name,以及Clazz返回指定的Bean
  public static <T> T getBean(String name, Class<T> clazz) {
    return getMyApplicationContext().getBean(name, clazz);
  }

  public static ApplicationContext getMyApplicationContext() {
    return myApplicationContext;
  }

  public static void setMyApplicationContext(ApplicationContext myApplicationContext) {
    BeanUtils.myApplicationContext = myApplicationContext;
  }
}
