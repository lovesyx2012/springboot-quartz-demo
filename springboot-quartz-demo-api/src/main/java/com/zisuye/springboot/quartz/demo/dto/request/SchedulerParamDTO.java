package com.zisuye.springboot.quartz.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SchedulerParamDTO {

  @Override
  public String toString() {
    return "SchedulerParamDTO{" +
        "id=" + id +
        ", title='" + title + '\'' +
        '}';
  }

  private Integer id;
  private String title;
}
