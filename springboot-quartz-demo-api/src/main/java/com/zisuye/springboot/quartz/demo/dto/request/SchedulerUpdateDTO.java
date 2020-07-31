package com.zisuye.springboot.quartz.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString
public class SchedulerUpdateDTO {

  @ApiModelProperty(value = "工作名,保持唯一", required = true)
  @JsonProperty("job_name")
  @NotNull(message = "工作名不能为空")
  private String jobName;

  @ApiModelProperty(value = "工作组名", required = true)
  @JsonProperty("job_group_name")
  @NotNull(message = "工作组名为空")
  private String jobGroupName;

  @ApiModelProperty(value = "执行时间规则", required = true)
  @JsonProperty("cron_express")
  @NotNull(message = "执行时间规则为空")
  private String cronExpress;

  @ApiModelProperty(value = "开始时间", required = true)
  @JsonProperty("start_time")
  @NotNull(message = "开始时间为空")
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
  private Date startTime;

  @DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
  @ApiModelProperty(value = "结束时间", required = true)
  @JsonProperty("end_time")
  @NotNull(message = "结束时间为空")
  private Date endTime;

  @ApiModelProperty(value = "任务发起回调地址  例如：mm", required = true)
  @JsonProperty("server_name")
  @NotNull(message = "任务发起回调地址为空")
  private String serverName;


  @ApiParam(name = "job_param", value = "回调请求参数", required = true)
  @ApiModelProperty(value = "回调请求参数", required = true)
  @JsonProperty("job_param")
  @NotNull(message = "回调请求参数为空")
  private String jobParam;

  @ApiModelProperty(value = "任务发起回调地址上下文  例如：/testJob", required = true)
  @JsonProperty("url_context")
  private String urlContext;
}
