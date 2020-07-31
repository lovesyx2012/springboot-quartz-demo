package com.zisuye.springboot.quartz.demo.intergate.impl;

import com.zisuye.springboot.quartz.demo.dto.base.Result;
import com.zisuye.springboot.quartz.demo.dto.request.SchedulerInvokeDTO;
import com.zisuye.springboot.quartz.demo.exception.BusinessException;
import com.zisuye.springboot.quartz.demo.exception.QuartzInvokeException;
import com.zisuye.springboot.quartz.demo.intergate.RpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class RpcServiceImpl implements RpcService {

  private static Logger logger = LoggerFactory.getLogger(
      RpcServiceImpl.class);

  //默认回调上下文
  public static final String URL_CONTEXT = "/service/callback";

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public Boolean executeCallback(SchedulerInvokeDTO callbackDTO) {
    String stringBuilder = callbackDTO.getServerName();
    //如果回调地址的上下文为空
    if (StringUtils.isEmpty(callbackDTO.getUrlContext())) {
      stringBuilder = "http://" + stringBuilder + URL_CONTEXT;
    } else {
      if (callbackDTO.getUrlContext().indexOf("/") == 0) {
        stringBuilder = "http://" + stringBuilder + callbackDTO.getUrlContext();
      } else {
        stringBuilder = "http://" + stringBuilder + "/" + callbackDTO.getUrlContext();
      }
    }
    HttpHeaders headers = new HttpHeaders();
    MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
    headers.setContentType(type);
    try {
      HttpEntity<Result<String>> requestEntity = new HttpEntity(
          callbackDTO.getJobParam(), headers);
      logger.info("requestEntity:{}", requestEntity);
      ResponseEntity<Result<Boolean>> responseEntity =
          restTemplate
              .exchange(stringBuilder,
                  HttpMethod.POST,
                  requestEntity,
                  new ParameterizedTypeReference<Result<Boolean>>() {
                  });
      if (null == responseEntity.getBody()) {
        logger.error("执行任务回调,response body is null 请求url is {}，请求参数is {} ", stringBuilder,
            callbackDTO.getJobParam());
        return false;
      }
      if (!responseEntity.getBody().ifSuccess()) {
        QuartzInvokeException
            .build(responseEntity.getBody().getCode(), responseEntity.getBody().getMessage());
        return false;
      }
      return true;
    } catch (BusinessException e) {
      logger
          .error("执行任务回调失败，请求url  is {} , 异常信息 is {},please check it! ,请求参数is{}", stringBuilder, e,
              callbackDTO.getJobParam());
    } catch (Exception e) {
      logger.error("执行任务回调发生异常，请求url  is {} ,异常信息 is {},请求参数is{},please check it! ",
          stringBuilder, e, callbackDTO.getJobParam());
    }
    return false;
  }

}
