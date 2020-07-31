package com.zisuye.springboot.quartz.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SchedulerBaseDTO {

  @ApiModelProperty(value = "工作名,保持唯一", required = true)
  @JsonProperty("job_name")
  @NotNull(message = "工作名不能为空")
  private String jobName;

  @ApiModelProperty(value = "工作组名,保持唯一", required = true)
  @JsonProperty("job_group_name")
  @NotNull(message = "工作组名不能为空")
  private String jobGroupName;


}
