package com.zisuye.springboot.quartz.demo.exception;

import com.zisuye.springboot.quartz.demo.dto.base.ResultCode;
import java.text.MessageFormat;
import org.springframework.util.StringUtils;

public class BaseException extends RuntimeException {

  protected ResultCode errCode;
  protected Object[] args;
  protected String code;

  public BaseException() {
  }

  public BaseException(String message) {
    super(message);
  }

  public BaseException(String message, Throwable cause) {
    super(message, cause);
  }

  public BaseException(Throwable cause) {
    super(cause);
  }

  public BaseException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public ResultCode getErrCode() {
    return this.errCode;
  }

  public void setErrCode(ResultCode errCode) {
    this.errCode = errCode;
  }

  public Object[] getArgs() {
    return this.args;
  }

  public String getMessage() {
    if (this.errCode != null && !StringUtils.isEmpty(this.errCode.getMessage())) {
      return this.args != null && this.args.length > 0 ? MessageFormat
          .format(this.errCode.getMessage(), this.args) : this.errCode.getMessage();
    } else {
      return super.getMessage();
    }
  }

  public String getCode() {
    return this.code != null ? this.code : (this.errCode != null ? this.errCode.getCode() : null);
  }

  public void setCode(String code) {
    this.code = code;
  }
}
