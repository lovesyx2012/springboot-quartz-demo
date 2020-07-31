package com.zisuye.springboot.quartz.demo.api;

import com.zisuye.springboot.quartz.demo.dto.base.Result;
import com.zisuye.springboot.quartz.demo.dto.request.SchedulerAddDTO;
import com.zisuye.springboot.quartz.demo.dto.request.SchedulerBaseDTO;
import com.zisuye.springboot.quartz.demo.dto.request.SchedulerParamDTO;
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
@Api(tags = {"任务调度回调（示例）"})
@RequestMapping("/invoke")
@Validated
public interface InvokeApi {

  @ApiOperation(value = "执行")
  @PostMapping(value = "/execute")
  public Result execute(@RequestBody SchedulerParamDTO schedulerParamDTO);

}
