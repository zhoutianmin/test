package test.demo.pachong.mapper;

import com.mongodb.client.result.DeleteResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import test.demo.pachong.entity.Hoopster;

import javax.annotation.Resource;

@Component
public class BasketballMapper {

    @Resource
    private MongoTemplate mongoTemplate;

    public void inset(Hoopster hoopster) {
        if (hoopster!=null){
            mongoTemplate.insert(hoopster, "hoopster");
        }

    }
}
