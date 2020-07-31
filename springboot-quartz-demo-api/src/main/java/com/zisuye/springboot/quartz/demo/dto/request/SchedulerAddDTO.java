package com.zisuye.springboot.quartz.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString
public class SchedulerAddDTO extends SchedulerBaseDTO {


  @ApiModelProperty(value = "触发时间点   例如：2019-06-13 18:18:18  注：当job_type=1，2 必送")
  @JsonProperty("trigger_time")
  @ApiParam(name = "trigger_time", value = "触发时间点 格式：yyyy-MM-dd hh:MM:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
  @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
  private Date triggerTime;

  @ApiModelProperty(value = "结束时间点   例如：2019-06-13 18:18:18 注：当job_type=3 必送")
  @JsonProperty("start_time")
  @ApiParam(name = "start_time", value = "结束时间点 格式：yyyy-MM-dd hh:MM:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
  @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
  private Date startTime;

  @ApiModelProperty(value = "结束时间点   例如：2019-06-13 18:18:18  注：当job_type=2，3 必送")
  @JsonProperty("end_time")
  @ApiParam(name = "end_time", value = "结束时间点 格式：yyyy-MM-dd hh:MM:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
  @JsonFormat(pattern = "yyyy-MM-dd hh:MM:ss")
  private Date endTime;

  @ApiModelProperty(value = "cron表达式 例如  0 0/1 0 * * ?  每一分钟执行一次  注：当job_type=3 必送")
  @JsonProperty("cron_express")
  private String cronExpress;

  @ApiModelProperty(value = "频率 和intervalUnit使用  注：当job_type=2 必送"
      + "比如 interval=1，intervalUnit=1 ：每1月执行一次")
  @JsonProperty("interval")
  private Integer interval;

  @ApiModelProperty(value = "单位 4：分钟 3：小时 2：天 和interval 使用 比如：interval=1，intervalUnit=4 ：每1分钟执行一次  注：当job_type=2 必送")
  @JsonProperty("interval_unit")
  private Integer intervalUnit;

  @ApiModelProperty(value = "server_name  回调地址 例如：hello-service或者127.0.0.1：8086", required = true)
  @JsonProperty("server_name")
  @NotNull(message = "回调服务名称")
  private String serverName;

  @ApiModelProperty(value = "回调参数 json字符串 例如：{\"A\":\"B\"}", required = true)
  @JsonProperty("job_param")
  @NotNull(message = "回调参数为空")
  private String jobParam;

  @ApiModelProperty(value = "url_context  回调地址 例如：/testJob  默认为/service/callback")
  @JsonProperty(value = "url_context")
  private String urlContext;

  @ApiModelProperty(value = "任务类型 1:单次任务  2:定时循环任务  3：定时循环任务支持cron表达式", required = true)
  @JsonProperty("job_type")
  @NotNull(message = "任务类型为空")
  @Min(value = 1, message = "任务类型最小为1")
  @Max(value = 3, message = "任务类型最大为3")
  private Integer jobType;

}
