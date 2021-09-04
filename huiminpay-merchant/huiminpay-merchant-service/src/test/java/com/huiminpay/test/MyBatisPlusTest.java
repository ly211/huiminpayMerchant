package com.huiminpay.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huiminpay.bean.Merchant;
import com.huiminpay.mapper.IMerchantMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    IMerchantMapper merchantMapper;

    /**
     * mybatis-plus使用默认crud时候，主键如果是自增，默认使用auto类型【也就是会自动回显id值】
     * mybatis进行添加数据时，如果没有配置主键select key，主键id不会回显
     */
    @Test
    public void insertTest(){
        Merchant merchant = new Merchant();
        merchant.setMerchantName("plus test");
        int insert = merchantMapper.insert(merchant);
        System.out.println("添加结果：" + insert);
        System.out.println("merchant: " + merchant);

    }

    @Test
    public void insertTest2(){
        Merchant merchant = new Merchant();
        merchant.setId(123L);
        merchant.setMerchantName("手动设置plus test");
        int insert = merchantMapper.insert(merchant);
        System.out.println("添加结果：" + insert);
        System.out.println("merchant: " + merchant);

    }

    @Test
    public void queryId(){
        Merchant merchant = merchantMapper.selectById(123L);
        System.out.println(merchant);
    }

    @Test
    public void queryByName(){
        QueryWrapper<Merchant> queryWrapper = new QueryWrapper<>();
        //参数一查询指定列 参数二指定值
        queryWrapper.eq("merchant_name","凤凰山");

        List<Merchant> merchants = merchantMapper.selectList(queryWrapper);
        System.out.println(merchants);
    }

    @Test
    public void pageQuery(){
        Page<Merchant> merchantPage = new Page<>(1, 5);
        //参数一指定分页信息 参数二搜索的条件
        IPage<Merchant> merchantIPage = merchantMapper.selectPage(merchantPage, null);

        List<Merchant> records = merchantIPage.getRecords();
        for (Merchant merchant : records) {
            System.out.println(merchant);
        }

    }

}
