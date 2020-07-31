package com.zisuye.springboot.quartz.demo.dto.base;

import java.io.Serializable;

public class ResultCode implements Serializable {

  public static final ResultCode SUCCESS = new ResultCode("000000");
  public static final ResultCode RESOURCE_NOT_EXIST = new ResultCode("100002", "资源不存在");
  public static final ResultCode UNKNOWN_ERR = new ResultCode("100003", "未知的服务错误");
  public static final ResultCode INTERNAL_ERR = new ResultCode("100004", "业务系统底层异常");
  public static final ResultCode INVALID_ARGS = new ResultCode("100005", "提交的参数验证不通过");
  private String code;
  private String message;

  private ResultCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  private ResultCode(String code) {
    this.code = code;
  }

  public ResultCode() {
  }

  public static ResultCode ofMessageAndCode(String message, String code) {
    return new ResultCode(code, message);
  }

  public static ResultCode of(String code, String message) {
    return new ResultCode(code, message);
  }

  public static ResultCode of(String code) {
    return new ResultCode(code);
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
