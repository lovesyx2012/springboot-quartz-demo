package com.zisuye.springboot.quartz.demo.utils;

import com.zisuye.springboot.quartz.demo.exception.BusinessException;
import java.util.Date;
import java.util.Map;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CronJobUtils {

  private static Logger log = LoggerFactory.getLogger(CronJobUtils.class);
  @Autowired
  @Qualifier("Scheduler")
  private Scheduler scheduler;

  /**
   * 添加定时任务
   */
  public void addJob(Class<? extends Job> jobClass, String jobName, String jobGroup,
      String cronExpress, Date startTime, Date endTime, Map<String, Object> dataMap) {
    //构建job信息
    JobDetail jobDetail = JobBuilder.newJob(jobClass)
        .withIdentity(jobName, jobGroup).build();
    jobDetail.getJobDataMap().putAll(dataMap);
    //表达式调度构建器(即任务执行的时间)
    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpress);

    //按新的cronExpression表达式构建一个新的trigger
    CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup)
        .withSchedule(scheduleBuilder).startAt(startTime).endAt(endTime).build();

    try {
      scheduler.scheduleJob(jobDetail, trigger);

    } catch (SchedulerException e) {
      log.warn("创建定时任务失败", e);
      throw new BusinessException("创建定时任务失败");
    }
  }

  /**
   * 修改定时任务
   */
  public void updateJob(Class<? extends Job> jobClass, String jobName, String jobGroup,
      String cronExpress, Date startTime, Date endTime, Map<String, Object> dataMap) {
    try {
      //构建job信息
      JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroup));
      if (jobDetail == null) {
        log.warn("要修改的job不存在！{}", jobName);
        return;
      }
      jobDetail.getJobDataMap().putAll(dataMap);
      scheduler.addJob(jobDetail, true, true);
      //表达式调度构建器(即任务执行的时间)
      CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpress);

      //按新的cronExpression表达式构建一个新的trigger
      CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup)
          .withSchedule(scheduleBuilder).startAt(startTime).endAt(endTime).build();
      scheduler.rescheduleJob(TriggerKey.triggerKey(jobName, jobGroup), trigger);
    } catch (SchedulerException e) {

      log.warn("修改定时任务失败", e);
      throw new BusinessException("修改定时任务失败");
    }


  }

  /**
   * 暂定定时任务
   */
  public void pauseJob(String jobName, String jobGroup) {
    try {
      JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroup));
      if (jobDetail != null) {
        scheduler.pauseJob(JobKey.jobKey(jobName, jobGroup));
      }
    } catch (SchedulerException e) {

      log.warn("暂停定时任务失败", e);
      throw new BusinessException("暂停定时任务失败");
    }
  }

  /**
   * 恢复定时任务
   */
  public void resumeJob(String jobName, String jobGroup) {
    try {
      JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroup));
      if (jobDetail != null) {
        scheduler.resumeJob(JobKey.jobKey(jobName, jobGroup));
      }
    } catch (SchedulerException e) {

      log.warn("重启定时任务失败", e);
      throw new BusinessException("重启定时任务失败");
    }
  }

  /**
   * 删除定时任务
   */
  public void deleteJob(String jobName, String jobGroup) {
    try {
      JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroup));
      if (jobDetail != null) {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, jobGroup));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, jobGroup));
        scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
      }
    } catch (SchedulerException e) {

      log.warn("删除定时任务失败", e);
      throw new BusinessException("删除定时任务失败");
    }
  }

  /**
   * 查询定时任务是否存在
   */
  public Boolean queryJobIsExist(String jobName, String jobGroup) {
    try {
      return scheduler.checkExists(JobKey.jobKey(jobName, jobGroup));
    } catch (SchedulerException e) {
      log.error("查询定时任务是否存在是失败", e);
      return false;
    }
  }
}
