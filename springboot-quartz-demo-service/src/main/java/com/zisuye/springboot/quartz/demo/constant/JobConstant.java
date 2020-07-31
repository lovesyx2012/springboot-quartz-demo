package com.zisuye.springboot.quartz.demo.constant;

public class JobConstant {

  public static final int JOB_TYPE_ONCE = 1;// 单次任务
  public static final int JOB_TYPE_CRON = 2;// 定时循环任务
  public static final int JOB_TYPE_CRON_EXPRESS = 3;// 定时循环任务支持cron表达式
}
