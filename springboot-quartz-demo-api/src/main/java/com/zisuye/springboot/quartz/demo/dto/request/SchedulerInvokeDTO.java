package com.zisuye.springboot.quartz.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SchedulerInvokeDTO {

  private String serverName ;

  @JsonProperty("job_param")
  private String jobParam ;

  @JsonProperty("url_context")
  private String urlContext ;

}
