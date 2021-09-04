package com.huiminpay.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 短信验证码测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SailingTest {

    @Test
    public void createSmsTest(){

        //创建RestTemplate进行远程调用
        RestTemplate restTemplate = new RestTemplate();

        //请求路径
        String url = "http://localhost:56085/sailing/generate?effectiveTime=300&name=sms";
        String phone = "15236952064";

        HashMap<String, Object> map = new HashMap<>();
        map.put("mobile",phone);

        //指定请求格式类型为json（请求头信息）
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        //将要发送的信息设置到请求头中进行发送
        HttpEntity<HashMap<String,Object>> hashMapHttpEntity = new HttpEntity<>(map, httpHeaders);

        //通过RestTemplate进行远程调用 ResponseEntity<Map>短信接口返回的数据存储在map集合中
        ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.POST, hashMapHttpEntity, Map.class);
        //获取返回结果
        Map exchangeBody = exchange.getBody();
        Set set = exchangeBody.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
