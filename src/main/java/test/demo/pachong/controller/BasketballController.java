package test.demo.pachong.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.demo.pachong.Basketball;
import test.demo.pachong.entity.Hoopster;
import test.demo.pachong.pipeline.MongoPipeline;
import test.pojo.SwaggerTest;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@Api(value = "篮球" , tags = "接口说明")
public class BasketballController {

    @Resource
    private MongoTemplate mongoTemplate;

    @ApiOperation(value = "爬取开始" , notes = "这是提示")
    @PostMapping(value = "/basketballaction")
    public void basketballaction(){
        Spider.create(new Basketball())
                .addUrl("https://data.qtx.com/basketball/lqjfb/wXn6J872B8_LNY6kaZ709.html")
                .addUrl("https://data.qtx.com/basketball/lqjfb/O5zWeOj29Q_qDZjMeZjVP.html")
//                .addUrl("https://data.qtx.com/basketball/lqqd/gR7vwnAjNQ.html")
//                .addUrl("https://data.qtx.com/basketball/lqqy/Ew7NkVXjJ9.html")
                .addPipeline(new ConsolePipeline())
//                .addPipeline(new JsonFilePipeline("D:\\javatest"))
                .addPipeline(new MongoPipeline())
                .thread(2)
                .run();

//        Query query = new Query();
//        List<Hoopster> all = mongoTemplate.findAll(Hoopster.class);
//        long count = all.size();
//        if (count > 0) {
//            // 删除后，就留下表明。字段和值都删除
//            mongoTemplate.remove(query, Hoopster.class);
//        }

    }

    @ApiOperation(value = "aa" , notes = "这是提示")
    @PostMapping(value = "/aa")
    public String aa(){
        return "aaaaa";
    }
}
