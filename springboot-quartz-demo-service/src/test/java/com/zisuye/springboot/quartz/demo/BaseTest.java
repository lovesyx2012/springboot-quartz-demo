package com.zisuye.springboot.quartz.demo;


import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.zisuye.springboot.quartz.demo.QuartzApplication.class)
public class BaseTest {
    @Test
    public void contextLoads() {
    }

    protected void printJson(Object o){
        System.out.println(JSON.toJSONString(o));
    }

}
