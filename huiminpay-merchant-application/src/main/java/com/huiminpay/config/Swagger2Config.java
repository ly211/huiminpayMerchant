package com.huiminpay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
/*@ConditionalOnProperty()*/
public class Swagger2Config {

    /**
     * 指定swagger生效的基础目录
     */
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                //指定生成的swagger文档基础信息
                .apiInfo(buildApiInfo())
                .select()
                //扫描基础包路径
                .apis(RequestHandlerSelectors.basePackage("com.huiminpay.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    /**
     * 构建api基础信息{网页中显示信息}
     * @return
     */
    public ApiInfo buildApiInfo(){
        Contact contact = new Contact("L","","");
        return new ApiInfoBuilder()
                .title("支付坑洞api接口文档")
                .description("该文档由后端写，供前端测试使用")
                .contact(contact)
                .version("V1.0.1")
                .build();
    }

}
