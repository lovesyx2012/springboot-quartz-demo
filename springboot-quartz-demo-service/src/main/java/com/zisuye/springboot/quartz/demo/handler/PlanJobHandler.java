package com.zisuye.springboot.quartz.demo.handler;

import com.zisuye.springboot.quartz.demo.dto.request.SchedulerInvokeDTO;
import com.zisuye.springboot.quartz.demo.intergate.RpcService;
import com.zisuye.springboot.quartz.demo.utils.BeanUtils;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PlanJobHandler implements Job {

  private static Logger logger = LoggerFactory.getLogger(PlanJobHandler.class);

  @Autowired
  RpcService rpcService;

  @Override
  public void execute(JobExecutionContext context) {

    JobDataMap map = context.getJobDetail().getJobDataMap();

    String serverName = map.get("serverName").toString();

    String jobParam = map.get("jobParam").toString();

    String urlContext = map.get("urlContext").toString();

    RpcService rpcService = BeanUtils.getBean(RpcService.class);

    SchedulerInvokeDTO callbackDTO = new SchedulerInvokeDTO();

    callbackDTO.setServerName(serverName);

    callbackDTO.setJobParam(jobParam);

    callbackDTO.setUrlContext(urlContext);

    Boolean ok = rpcService.executeCallback(callbackDTO);

    logger.info("PlanJobHandler  -- 任务回调" + (ok ? "成功" : "失败") + ",参数为：{}", jobParam);
  }
}
