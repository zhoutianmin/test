package test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;
import test.demo.pachong.User;
import test.pojo.SwaggerTest;

import javax.annotation.Resource;

@Slf4j
@RestController
@Api(value = "接口说明" , tags = "接口说明")
public class SwaggerTestController {

    //http://localhost:8080/swagger-ui.html

    @Resource
    private MongoTemplate mongoTemplate;

    @ApiOperation(value = "新增post" , notes = "这是提示")
    @PostMapping(value = "/testPost")
    public void testPost(@RequestBody SwaggerTest t){

    }

    @ApiOperation(value = "方法描述" , notes = "提示内容")
    @GetMapping("/testGet")
    public void testGet(@PathVariable @ApiParam(name = "参数名",value = "参数说明",required = true) Integer i){

    }

    @ApiOperation(value = "传参测试" , notes = "提示内容")
    @GetMapping("/testcc")
    public String testcc(String a,String b, Integer c){
        System.out.println(a);
        System.out.println(b);
        return a;
    }

    @ApiOperation(value = "mongo测试" , notes = "提示内容")
    @GetMapping("/mongo")
    public void testMongo(String a,String b, Integer c){
        User user = new User();
        user.setName(a);
        user.setAddress(b);
        mongoTemplate.insert(user,"testc");
    }

    public void test(){
        System.out.println("aaaadddffff");
    }
}
