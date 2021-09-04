package com.huiminpay.api;

/**
 * 验证码接口
 */
public interface ISmsService {

    /**
     * 获取验证码
     * @param phone
     * @return
     */
    public String sendSms(String phone);

    /**
     * 校验验证码
     * 验证码key值由前端传递
     * @param verifyKey
     * 验证码value值由用户输入
     * @param verifyCode
     * @return
     */
    public String verify(String verifyKey,String verifyCode);

}
