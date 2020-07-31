package com.zisuye.springboot.quartz.demo.utils;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import com.zisuye.springboot.quartz.demo.exception.BusinessException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SimpleJobUtils {

  private static Logger log = LoggerFactory.getLogger(SimpleJobUtils.class);
  @Autowired
  @Qualifier("Scheduler")
  private Scheduler scheduler;

  /**
   * 添加定时任务
   */
  public void addOneTimeJob(Class<? extends Job> jobClass, String jobName, String jobGroup,
      Date triggerTime, Map<String, Object> dataMap) {
    //构建job信息
    JobDetail jobDetail = JobBuilder.newJob(jobClass)
        .withIdentity(jobName, jobGroup).build();
    jobDetail.getJobDataMap().putAll(dataMap);
    Trigger trigger = newTrigger()
        .withIdentity(jobName, jobGroup)
        .startAt(triggerTime)
        .build();
    try {
      scheduler.scheduleJob(jobDetail, trigger);
    } catch (SchedulerException e) {
      log.warn("创建定时任务失败", e);
      throw new BusinessException("创建定时任务失败");
    }
  }

  /**
   * 添加定时任务
   */
  public void addRepeatJob(Class<? extends Job> jobClass, String jobName, String jobGroup,
      Date triggerTime, Date endTime, Integer interval, TimeUnit intervalUnit,
      Map<String, Object> dataMap) {
    //构建job信息
    JobDetail jobDetail = JobBuilder.newJob(jobClass)
        .withIdentity(jobName, jobGroup).build();
    jobDetail.getJobDataMap().putAll(dataMap);
    TriggerBuilder<Trigger> builder = newTrigger()
        .withIdentity(jobName, jobGroup);
    if (intervalUnit == TimeUnit.DAYS) {
      builder.withSchedule(simpleSchedule().withIntervalInHours(interval * 24).repeatForever());
    } else if (intervalUnit == TimeUnit.HOURS) {
      builder.withSchedule(simpleSchedule().withIntervalInHours(interval).repeatForever());
    } else if (intervalUnit == TimeUnit.MINUTES) {
      builder.withSchedule(simpleSchedule().withIntervalInMinutes(interval).repeatForever());
    } else if (intervalUnit == TimeUnit.SECONDS) {
      builder.withSchedule(simpleSchedule().withIntervalInSeconds(interval).repeatForever());
    }

    Trigger trigger = builder
        .startAt(triggerTime)
        .endAt(endTime)
        .build();

    try {
      scheduler.scheduleJob(jobDetail, trigger);
    } catch (SchedulerException e) {
      log.warn("创建定时任务失败", e);
      throw new BusinessException("创建定时任务失败");
    }
  }
}
