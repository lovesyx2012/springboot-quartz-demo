package com.zisuye.springboot.quartz.demo.exception;


import com.zisuye.springboot.quartz.demo.dto.base.ResultCode;

public class BusinessException extends BaseException {
  public BusinessException() {
  }

  public BusinessException(ResultCode errCode) {
    this(errCode, null);
  }

  public BusinessException(ResultCode errCode, Object... args) {
    this.errCode = errCode;
    this.args = args;
  }

  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(String message, String code) {
    super(message);
    this.code = code;
  }

  public BusinessException(String message, Throwable cause, String code) {
    super(message, cause);
    this.code = code;
  }

  public BusinessException(Throwable cause, String code) {
    super(cause);
    this.code = code;
  }

  public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.code = code;
  }
}
