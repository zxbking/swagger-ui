# swagger-ui-helper
进行swagger-ui进行封装
### 步骤一：引入配置文件，使用2.8.0版本
```xml
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.8.0</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.8.0</version>
        </dependency>
```
### 步骤二：创建swagger配置类，设置拦截需要显示的api
```java
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
/**
 * Created by zhangxibin on 2016/8/9.
 */

@Configuration
@EnableSwagger2
@ConditionalOnProperty(value = "com.zxbking.swagger.import", havingValue = "true")
public class SwaggerConfig {
    @Value("${com.zxbking.swagger.title:APPNAME}")
    private String title;
    @Value("${com.zxbking.swagger.description:文档中可以查询及测试接口调用参数和结果}")
    private String description;
    @Value("${com.zxbking.swagger.version:V1.0}")
    private String version;
    @Bean
    public Docket app_api() {
        return new Docket(DocumentationType.SWAGGER_2).pathMapping("/").select().apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))//错误路径不监控
                .paths(Predicates.not(PathSelectors.regex("/rest.*")))
//                .paths(Predicates.not(PathSelectors.regex("/open.*")))
                .paths(PathSelectors.regex("/.*"))
                .build().groupName(title+"公开接口文档"+version)
                .apiInfo(apiInfo(title+"公开接口文档",description,version));
    }
    @Bean
    public Docket app_rest_api() {
        return new Docket(DocumentationType.SWAGGER_2).pathMapping("/").select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/rest/.*")).build().groupName(title+"鉴权接口文档"+version)
                .apiInfo(apiInfo(title+"鉴权接口文档",description,version));
    }


    private ApiInfo apiInfo(String name,String description,String version) {
        ApiInfo apiInfo = new ApiInfoBuilder().title(name).description(description).version(version).build();
        return apiInfo;
    }
}
```
其中`@ConditionalOnProperty(value = "com.zxbking.swagger.import", havingValue = "true")`使用变量控制是否启用swagger
在application.properties文件中增加com.zxbking.swagger.import参数，为true是启动swagger，为false时不启动swagger。
### 步骤三：使用对api进行分组
类名上使用tags进行分组
```java
@Api(tags = {"类型1"},description = "测试api内容",position = 30)
```
方法名上使用tags进行分组
```java
  @ApiOperation(
            value = "Find purchase order by ID",
            notes = "For valid response try integer IDs with value <= 5 or > 10. Other values will generated exceptions",
            tags = {"Pet Store"})
```
