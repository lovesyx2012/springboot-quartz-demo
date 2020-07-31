package com.zisuye.springboot.quartz.demo.scheduler;


import com.alibaba.fastjson.JSON;
import com.zisuye.springboot.quartz.demo.BaseTest;
import com.zisuye.springboot.quartz.demo.constant.JobConstant;
import com.zisuye.springboot.quartz.demo.dto.request.SchedulerAddDTO;
import com.zisuye.springboot.quartz.demo.service.SchedulerService;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SchedulerTest extends BaseTest {


  @Autowired
  private SchedulerService schedulerService;

  @Test
  public void testRegisterScheduler() {
    SchedulerAddDTO schedulerAddDTO = new SchedulerAddDTO();
    schedulerAddDTO.setJobName("xxx_job_name1");
    schedulerAddDTO.setJobGroupName("xxx_job_group_name1");
    schedulerAddDTO.setServerName("127.0.0.1:8806");
    schedulerAddDTO.setJobParam("{\"id\":1,\"name\":\"aaa\"}");
    schedulerAddDTO.setUrlContext("/invoke/execute");
    schedulerAddDTO.setJobType(JobConstant.JOB_TYPE_ONCE);
    schedulerAddDTO.setTriggerTime(new Date());
    schedulerService.registerScheduler(schedulerAddDTO);
  }
}
