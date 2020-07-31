package com.zisuye.springboot.quartz.demo.service;

import com.zisuye.springboot.quartz.demo.dto.request.SchedulerAddDTO;
import com.zisuye.springboot.quartz.demo.dto.request.SchedulerBaseDTO;
import java.util.Date;
import org.quartz.Job;

public interface SchedulerService {

  //添加一次任务
  Boolean registerScheduler(SchedulerAddDTO schedulerAddDTO);

  //更新任务
  Boolean updateScheduler(Class<? extends Job> jobClass, String jobName, String jobGroup,
      String cronExpress, Date startTime, Date endTime, String serviceName, String jobParam,
      String urlContext);

  //暂停任务
  Boolean pauseScheduler(SchedulerBaseDTO schedulerBaseDTO);

  //恢复任务
  Boolean resumeScheduler(SchedulerBaseDTO schedulerBaseDTO);

  //删除任务
  Boolean deleteScheduler(SchedulerBaseDTO schedulerBaseDTO);

  //查询定时任务是否存在
  Boolean querySchedulerIsExist(String jobName, String jobGroup);
}
