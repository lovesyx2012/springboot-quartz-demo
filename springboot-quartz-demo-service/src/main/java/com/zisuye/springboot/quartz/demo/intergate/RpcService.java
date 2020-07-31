package com.zisuye.springboot.quartz.demo.intergate;

import com.zisuye.springboot.quartz.demo.dto.request.SchedulerInvokeDTO;

public interface RpcService {

  Boolean executeCallback(SchedulerInvokeDTO callbackDTO);
}
