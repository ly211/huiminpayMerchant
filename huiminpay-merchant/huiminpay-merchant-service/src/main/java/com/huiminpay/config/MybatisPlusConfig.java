package com.huiminpay.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.huiminpay.**.mapper")
public class MybatisPlusConfig {

    /**
     * 创建拦截器对象用于实现分页查询（和学习pageHelper时配置拦截器一样的意思）
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
