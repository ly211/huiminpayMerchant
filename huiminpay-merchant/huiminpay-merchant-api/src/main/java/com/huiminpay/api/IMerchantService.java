package com.huiminpay.api;

import com.huiminpay.api.dto.MerchantDto;
import com.huiminpay.bean.Merchant;

/**
 * 商铺业务层接口
 */
public interface IMerchantService {

    public Merchant queryMerchantById(Long id);

    /**
     * 商户注册
     * @param merchantDto
     * @return
     */
    MerchantDto createMerchant(MerchantDto merchantDto);
}
