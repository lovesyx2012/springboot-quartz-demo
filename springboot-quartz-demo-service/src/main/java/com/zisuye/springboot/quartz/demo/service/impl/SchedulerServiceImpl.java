package com.zisuye.springboot.quartz.demo.service.impl;

import com.zisuye.springboot.quartz.demo.dto.base.ResultCode;
import com.zisuye.springboot.quartz.demo.dto.request.SchedulerAddDTO;
import com.zisuye.springboot.quartz.demo.dto.request.SchedulerBaseDTO;
import com.zisuye.springboot.quartz.demo.exception.QuartzInvokeException;
import com.zisuye.springboot.quartz.demo.handler.PlanJobHandler;
import com.zisuye.springboot.quartz.demo.service.SchedulerService;
import com.zisuye.springboot.quartz.demo.utils.CronJobUtils;
import com.zisuye.springboot.quartz.demo.utils.SimpleJobUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.quartz.CronExpression;
import org.quartz.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulerServiceImpl implements SchedulerService {

  private static Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);

  @Autowired
  private SimpleJobUtils simpleJobUtils;

  @Autowired
  private CronJobUtils cronJobUtils;

  @Override
  public Boolean registerScheduler(SchedulerAddDTO schedulerAddDTO) {
    //1.检验参数
    checkParam(schedulerAddDTO);
    //2.增加计划  1:单次任务  2:定时循环任务  3：定时循环任务支持cron表达式
    if (cronJobUtils.queryJobIsExist(schedulerAddDTO.getJobName(),
        schedulerAddDTO.getJobGroupName())) {
      QuartzInvokeException.build(ResultCode.INVALID_ARGS.getCode(),
          schedulerAddDTO.getJobName() + "." + schedulerAddDTO.getJobGroupName() + "已经存在");
    }
    switch (schedulerAddDTO.getJobType()) {
      case 1:
        simpleJobUtils.addOneTimeJob(
            PlanJobHandler.class, schedulerAddDTO.getJobName(),
            schedulerAddDTO.getJobGroupName(), schedulerAddDTO.getTriggerTime(),
            setParamMap(schedulerAddDTO.getServerName(), schedulerAddDTO.getJobParam(),
                schedulerAddDTO.getUrlContext()));
        break;
      case 2:
        simpleJobUtils
            .addRepeatJob(PlanJobHandler.class, schedulerAddDTO.getJobName(),
                schedulerAddDTO.getJobGroupName(),
                schedulerAddDTO.getTriggerTime(), schedulerAddDTO.getEndTime(),
                schedulerAddDTO.getInterval(),
                toTimeUnit(schedulerAddDTO.getIntervalUnit()),
                setParamMap(schedulerAddDTO.getServerName(), schedulerAddDTO.getJobParam(),
                    schedulerAddDTO.getUrlContext()));
        break;
      case 3:
        cronJobUtils.addJob(PlanJobHandler.class, schedulerAddDTO.getJobName(),
            schedulerAddDTO.getJobGroupName(),
            schedulerAddDTO.getCronExpress(), schedulerAddDTO.getStartTime(),
            schedulerAddDTO.getEndTime(),
            setParamMap(schedulerAddDTO.getServerName(), schedulerAddDTO.getJobParam(),
                schedulerAddDTO.getUrlContext()));
        break;
    }
    return true;
  }

  @Override
  public Boolean updateScheduler(Class<? extends Job> jobClass, String jobName, String jobGroup,
      String cronExpress, Date startTime, Date endTime, String serverName, String jobParam,
      String urlContext) {
    cronJobUtils.updateJob(PlanJobHandler.class, jobName, jobGroup,
        cronExpress, startTime, endTime, setParamMap(serverName, jobParam, urlContext));
    return true;
  }

  @Override
  public Boolean pauseScheduler(SchedulerBaseDTO schedulerBaseDTO) {
    cronJobUtils.pauseJob(schedulerBaseDTO.getJobName(), schedulerBaseDTO.getJobGroupName());
    return true;
  }

  @Override
  public Boolean resumeScheduler(SchedulerBaseDTO schedulerBaseDTO) {
    cronJobUtils.resumeJob(schedulerBaseDTO.getJobName(), schedulerBaseDTO.getJobGroupName());
    return true;
  }

  @Override
  public Boolean deleteScheduler(SchedulerBaseDTO schedulerBaseDTO) {
    cronJobUtils.deleteJob(schedulerBaseDTO.getJobName(), schedulerBaseDTO.getJobGroupName());
    return true;
  }

  @Override
  public Boolean querySchedulerIsExist(String jobName, String jobGroup) {
    return cronJobUtils.queryJobIsExist(jobName, jobGroup);
  }

  private Map<String, Object> setParamMap(String serverName,
      String jobParam, String urlContext) {
    HashMap<String, Object> dataMap = new HashMap<>();
    dataMap.put("serverName", serverName);
    dataMap.put("jobParam", jobParam);
    dataMap.put("urlContext", urlContext);
    logger.info("dataMap:{}", dataMap);
    return dataMap;
  }

  //检验参数  //2：每天   3.每小时  4.每分钟
  private void checkParam(SchedulerAddDTO schedulerAddDTO) {
    Integer getJobType = schedulerAddDTO.getJobType();
    if (Objects.equals(getJobType, 1)) {
      //单次任务  triggerTime 不能为空
      if (Objects.isNull(schedulerAddDTO.getTriggerTime())) {
        QuartzInvokeException.build(ResultCode.INVALID_ARGS.getCode(), "结束时间为空");
      }
    }
    if (Objects.equals(schedulerAddDTO, 2)) {
      //多次任务  triggerTime endTime  interval intervalUnit 不能为空
      if (Objects.isNull(schedulerAddDTO.getTriggerTime())) {
        QuartzInvokeException.build(ResultCode.INVALID_ARGS.getCode(), "触发时间为空");
      }
      if (Objects.isNull(schedulerAddDTO.getEndTime())) {
        QuartzInvokeException.build(ResultCode.INVALID_ARGS.getCode(), "结束时间为空");
      }
      if (Objects.isNull(schedulerAddDTO.getInterval())) {
        QuartzInvokeException.build(ResultCode.INVALID_ARGS.getCode(), "频率为空");
      }
      if (Objects.isNull(schedulerAddDTO.getIntervalUnit())) {
        QuartzInvokeException.build(ResultCode.INVALID_ARGS.getCode(), "单位为空");
      }

    }
    if (Objects.equals(schedulerAddDTO.getJobType(), 3)) {
      //定时循环任务支持cron表达式  cronExpress  startTime  endTime
      if (Objects.isNull(schedulerAddDTO.getCronExpress())) {
        QuartzInvokeException.build(ResultCode.INVALID_ARGS.getCode(), "触发时间为空");
      }
      if (Objects.isNull(schedulerAddDTO.getStartTime())) {
        QuartzInvokeException.build(ResultCode.INVALID_ARGS.getCode(), "开始时间为空");
      }
      if (Objects.isNull(schedulerAddDTO.getEndTime())) {
        QuartzInvokeException.build(ResultCode.INVALID_ARGS.getCode(), "结束时间为空");
      }
      if (!CronExpression.isValidExpression(schedulerAddDTO.getCronExpress())) {
        QuartzInvokeException.build(ResultCode.INVALID_ARGS.getCode(), "cron_express 表达式错误");
      }
    }
  }


  //2：每天   3.每小时  4.每分钟
  private TimeUnit toTimeUnit(Integer IntervalUnit) {
    switch (IntervalUnit) {
      case 2:
        //语句
        return TimeUnit.DAYS;
      case 3:
        //语句
        return TimeUnit.HOURS;
      case 4:
        //语句
        return TimeUnit.MINUTES;
    }
    return null;
  }
}
