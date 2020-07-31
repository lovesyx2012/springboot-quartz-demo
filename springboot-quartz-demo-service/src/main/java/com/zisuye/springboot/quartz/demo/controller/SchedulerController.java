package com.zisuye.springboot.quartz.demo.controller;

import com.zisuye.springboot.quartz.demo.api.SchedulerApi;
import com.zisuye.springboot.quartz.demo.dto.base.Result;
import com.zisuye.springboot.quartz.demo.dto.request.SchedulerAddDTO;
import com.zisuye.springboot.quartz.demo.dto.request.SchedulerBaseDTO;
import com.zisuye.springboot.quartz.demo.dto.request.SchedulerUpdateDTO;
import com.zisuye.springboot.quartz.demo.handler.PlanJobHandler;
import com.zisuye.springboot.quartz.demo.service.SchedulerService;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchedulerController implements SchedulerApi {

  @Autowired
  private SchedulerService schedulerService;

  @Override
  public Result<Boolean> addOnceScheduler(@Valid @NotNull SchedulerAddDTO schedulerAddDTO) {
    return Result.success(schedulerService.registerScheduler(schedulerAddDTO));
  }

  @Override
  public Result<Boolean> updateScheduler(@Valid @NotNull SchedulerUpdateDTO schedulerUpdateDTO) {
    return Result.success(
        schedulerService.updateScheduler(PlanJobHandler.class,
            schedulerUpdateDTO.getJobName(),
            schedulerUpdateDTO.getJobGroupName(),
            schedulerUpdateDTO.getCronExpress(),
            schedulerUpdateDTO.getStartTime(),
            schedulerUpdateDTO.getEndTime(),
            schedulerUpdateDTO.getServerName(),
            schedulerUpdateDTO.getJobParam(),
            schedulerUpdateDTO.getUrlContext()));
  }

  @Override
  public Result<Boolean> pauseScheduler(@Valid @NotNull SchedulerBaseDTO schedulerBaseDTO) {
    return Result.success(schedulerService.pauseScheduler(schedulerBaseDTO));
  }

  @Override
  public Result<Boolean> resumeScheduler(@Valid @NotNull SchedulerBaseDTO schedulerBaseDTO) {
    return Result.success(schedulerService.resumeScheduler(schedulerBaseDTO));
  }

  @Override
  public Result<Boolean> deleteScheduler(@Valid @NotNull SchedulerBaseDTO schedulerBaseDTO) {
    return Result.success(schedulerService.deleteScheduler(schedulerBaseDTO));
  }

  @Override
  public Result<Boolean> querySchedulerIsExist(String jobName, String jobGroupName) {
    return Result.success(schedulerService.querySchedulerIsExist(jobName,jobGroupName));
  }
}
