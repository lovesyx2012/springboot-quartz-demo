package com.zisuye.springboot.quartz.demo.api;

import com.zisuye.springboot.quartz.demo.dto.base.Result;
import com.zisuye.springboot.quartz.demo.dto.request.SchedulerAddDTO;
import com.zisuye.springboot.quartz.demo.dto.request.SchedulerBaseDTO;
import com.zisuye.springboot.quartz.demo.dto.request.SchedulerUpdateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"任务调度服务"})
@RequestMapping("/schedule")
@Validated
public interface SchedulerApi {

  @ApiOperation(value = "注册计划任务")
  @PostMapping("/registerScheduler")
  Result<Boolean> addOnceScheduler(
      @RequestBody @Valid @NotNull SchedulerAddDTO schedulerAddDTO
  );


  @ApiOperation(value = "更新计划任务")
  @PostMapping(value = "/updateScheduler")
  Result<Boolean> updateScheduler(
      @RequestBody @Valid @NotNull
          SchedulerUpdateDTO schedulerUpdateDTO
  );

  @ApiOperation(value = "暂停计划任务")
  @PostMapping(value = "/pauseScheduler")
  Result<Boolean> pauseScheduler(
      @RequestBody @Valid @NotNull SchedulerBaseDTO schedulerBaseDTO
  );

  @ApiOperation(value = "恢复计划任务")
  @PostMapping(value = "/resumeScheduler")
  Result<Boolean> resumeScheduler(

      @RequestBody @Valid @NotNull SchedulerBaseDTO schedulerBaseDTO
  );

  @ApiOperation(value = "删除计划任务")
  @PostMapping(value = "/deleteScheduler")
  Result<Boolean> deleteScheduler(
      @RequestBody @Valid @NotNull SchedulerBaseDTO schedulerBaseDTO
  );


  @ApiOperation(value = "查询是否存在计划任务")
  @GetMapping("/schedulerIsExist")
  Result<Boolean> querySchedulerIsExist(
      @RequestParam(value = "job_name") @ApiParam(name = "job_name", value = "工作名", required = true) String jobName,
      @RequestParam(value = "job_group_name") @ApiParam(name = "job_group_name", value = "工作组名", required = true) String jobGroupName
  );

}
