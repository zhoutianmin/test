package test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //测试API
    @Bean
    public Docket myDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("测试")
                .apiInfo(myApiInfo())//调用的api描述方法
                .select()
                .apis(RequestHandlerSelectors.basePackage("test.demo"))//扫描的API包路径
                .build();
    }

    public ApiInfo myApiInfo() {
        return new ApiInfoBuilder()
                .title("测试API文档")
                .version("1.0")
                .build();
    }
}
