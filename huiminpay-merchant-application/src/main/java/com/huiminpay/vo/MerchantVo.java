package com.huiminpay.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@ApiModel(value = "MerchantRegisterVo",description = "商户注册")
@Data
public class MerchantVo implements Serializable {

    @ApiModelProperty("商户手机号")
    private String mobile;

    @ApiModelProperty("商户用户名")
    private String username;

    @ApiModelProperty("商户密码")
    private String password;

    @ApiModelProperty("验证码key值")
    private String verifiyKey;

    @ApiModelProperty("验证码")
    private String verifiyCode;

}
