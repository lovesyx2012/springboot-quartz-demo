package com.zisuye.springboot.quartz.demo.dto.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(
    description = "返回结果"
)
public class Result<T> implements Serializable {

  public static final String INTERNAL_ERR = "100004";
  public static final String INVALID_ARG = "100005";
  protected static final String SUCCESS_CODE = "000000";
  protected static final String UNKNOWN_ERR = "100003";
  @ApiModelProperty("说明")
  private String message = "";
  @ApiModelProperty("响应码,000000代表成功")
  private String code;
  @ApiModelProperty("响应数据")
  private T data;

  public Result() {
  }

  public static <E> Result<E> success(E data) {
    Result<E> result = new Result();
    result.setData(data);
    result.setCode("000000");
    return result;
  }

  public static <E> Result<E> fail(E data, String message, String code) {
    Result<E> result = new Result();
    result.setMessage(message);
    result.setCode(code);
    return result;
  }

  public static <E> Result<E> fail(String message, String code) {
    Result<E> result = new Result();
    result.setMessage(message);
    result.setCode(code);
    return result;
  }

  public static <E> Result<E> fail(String message) {
    Result<E> result = new Result();
    result.setMessage(message);
    result.setCode("100003");
    return result;
  }

  public static <E> Result<E> fail(ResultCode resultCode) {
    Result<E> result = new Result();
    result.setMessage(resultCode.getMessage());
    result.setCode(resultCode.getCode());
    return result;
  }

  public static <E> Result<E> fail(ResultCode resultCode, String message) {
    Result<E> result = new Result();
    result.setMessage(message);
    result.setCode(resultCode.getCode());
    return result;
  }

  public static <E> Result<E> fail(E data, ResultCode resultCode, String message) {
    Result<E> result = new Result();
    result.setData(data);
    result.setMessage(message);
    result.setCode(resultCode.getCode());
    return result;
  }

  public static <E> Result<E> fail(E data, ResultCode resultCode) {
    Result<E> result = new Result();
    result.setData(data);
    result.setCode(resultCode.getCode());
    result.setMessage(resultCode.getMessage());
    return result;
  }

  public boolean ifSuccess() {
    return "000000".equals(this.code);
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public T getData() {
    return this.data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String toString() {
    return "Result{message='" + this.message + '\'' + ", code='" + this.code + '\'' + ", data="
        + this.data + '}';
  }
}
