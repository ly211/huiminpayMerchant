package com.huiminpay.controller;


import com.huiminpay.api.IMerchantService;
import com.huiminpay.api.ISmsService;
import com.huiminpay.api.dto.MerchantDto;
import com.huiminpay.bean.Merchant;
import com.huiminpay.vo.MerchantVo;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

@Api(value = "商铺应用API接口",description = "商户API接口包含增删改查")
@RestController
@RequestMapping("merchant")
public class MerchantController {

    @org.apache.dubbo.config.annotation.Reference
    IMerchantService merchantService;

    @org.apache.dubbo.config.annotation.Reference
    ISmsService smsService;

    @ApiOperation(value = "根据商铺id获取商铺信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantId",value = "商铺id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "merchantName",value = "商铺名字", required = false, dataType = "string")
    })
    @GetMapping("/queryMerchant/{merchantId}/{merchantName}")
    public Merchant queryMerchant(@PathVariable("merchantId") Long id,
                                  @PathVariable("merchantName") String name){
        System.out.println(name);
        return merchantService.queryMerchantById(id);

    }

    @ApiOperation("根据传递的merchant信息返回结果")
    @PostMapping("/query")
    public String query(Merchant merchant){

        return merchant.toString();
    }

    @ApiImplicitParam(name = "phone",value = "手机号", required = true, paramType = "path")
    @GetMapping("/sendPhone/{phone}")
    public String sendSms(@PathVariable("phone") String phone){

        //调用业务层返回key值
       return smsService.sendSms(phone);
    }

    /**
     * 商户注册接口
     */
    @PostMapping("/registerMerchant")
    public MerchantVo registerMerchant(@RequestBody MerchantVo merchantVo){

        //校验验证码
        smsService.verify(merchantVo.getVerifiyKey(),merchantVo.getVerifiyCode());

        //商户注册
        MerchantDto merchantDto = new MerchantDto();
        merchantDto.setMobile(merchantVo.getMobile());
        merchantService.createMerchant(merchantDto);

        return merchantVo;
    }

}
