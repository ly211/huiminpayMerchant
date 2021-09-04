package com.huiminpay.service;

import com.alibaba.fastjson.JSON;
import com.huiminpay.api.ISmsService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@org.apache.dubbo.config.annotation.Service
public class SmsServiceImpl implements ISmsService {

    @Value("${sms.url}")
    String url;

    @Value("${sms.effectiveTime}")
    String effectiveTime;

    @Value("${sms.name}")
    String name;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String sendSms(String phone) {

        //请求路径
        String smsUrl = url + "generate?effectiveTime=" + effectiveTime + "&name=" + name;

        HashMap<String, Object> map = new HashMap<>();
        map.put("mobile",phone);

        //指定请求格式类型为json（请求头信息）
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        //将要发送的信息设置到请求头中进行发送
        HttpEntity<HashMap<String,Object>> hashMapHttpEntity = new HttpEntity<>(map, httpHeaders);

        //通过RestTemplate进行远程调用 ResponseEntity<Map>短信接口返回的数据存储在map集合中
        ResponseEntity<Map> exchange = restTemplate.exchange(smsUrl, HttpMethod.POST, hashMapHttpEntity, Map.class);

        //获取验证码对应key值进行报讯
        if (exchange != null){
            Map resultMap = exchange.getBody();
            Object results = resultMap.get("result");

            if (results == null){  //代表验证码获取失败
                throw new RuntimeException("验证码获取错误");
            }

            Map<String,String> result = (Map<String,String>) results;
            String key = result.get("key");
            return key;
        }
        return null;
    }

    @Override
    public String verify(String verifyKey, String verifyCode) {
        String verifyUrl = url + "/verify?name=sms&verificationCode=" + verifyCode + "&verificationKey=" + verifyKey;
        Map responseMap = null;
        try {
            //请求校验验证码
            ResponseEntity<Map> exchange = restTemplate.exchange(verifyUrl, HttpMethod.POST,
                    HttpEntity.EMPTY, Map.class);
            responseMap = exchange.getBody();
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new RuntimeException("验证码错误");
        }
        if (responseMap == null || responseMap.get("result") == null || !(Boolean)
                responseMap.get("result")) {
            throw new RuntimeException("验证码错误");
        }
        return null;
    }

}
