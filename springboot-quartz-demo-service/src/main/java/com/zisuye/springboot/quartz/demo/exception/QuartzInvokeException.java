package com.zisuye.springboot.quartz.demo.exception;

import com.zisuye.springboot.quartz.demo.dto.base.ResultCode;

public class QuartzInvokeException extends BusinessException {

  private static final long serialVersionUID = 1L;

  public QuartzInvokeException(String message, String code) {
    super(message, code);
  }

  /**
   *
   */
  public static void build(ResultCode resultCode) {
    throw new QuartzInvokeException(resultCode.getMessage(), resultCode.getCode());
  }

  /**
   *
   */
  public static void build(String code, String message) {
    throw new QuartzInvokeException(message, code);
  }
}
