package com.huiminpay.service;

import com.huiminpay.api.IMerchantService;
import com.huiminpay.api.dto.MerchantDto;
import com.huiminpay.bean.Merchant;
import com.huiminpay.mapper.IMerchantMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@org.apache.dubbo.config.annotation.Service
public class MerchantServiceImpl implements IMerchantService {

    @Autowired
    IMerchantMapper merchantMapper;

    @Override
    public Merchant queryMerchantById(Long id) {
        return merchantMapper.selectById(id);
    }

    /**
     * 将商户信息进行注册（存储到数据库）
     * @param merchantDto
     * @return
     */
    @Override
    public MerchantDto createMerchant(MerchantDto merchantDto) {
        Merchant merchant = new Merchant();
        merchant.setAuditStatus("0");
        merchant.setMobile(merchantDto.getMobile());
        merchantMapper.insert(merchant);

        //将添加成功后的id回显到dto中返回给前端
        merchantDto.setId(merchant.getId());
        return merchantDto;
    }
}
