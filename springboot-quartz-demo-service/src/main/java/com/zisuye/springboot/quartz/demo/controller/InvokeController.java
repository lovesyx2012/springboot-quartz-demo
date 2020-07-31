package com.zisuye.springboot.quartz.demo.controller;

import com.zisuye.springboot.quartz.demo.api.InvokeApi;
import com.zisuye.springboot.quartz.demo.dto.base.Result;
import com.zisuye.springboot.quartz.demo.dto.request.SchedulerParamDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvokeController implements InvokeApi {

  @Override
  public Result execute(SchedulerParamDTO schedulerParamDTO) {
    System.out.println(schedulerParamDTO);
    System.out.println("just do whatever you want..");
    return null;
  }
}
