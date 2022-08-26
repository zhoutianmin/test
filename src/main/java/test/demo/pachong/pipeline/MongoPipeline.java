package test.demo.pachong.pipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import test.demo.pachong.User;
import test.demo.pachong.entity.Hoopster;
import test.demo.pachong.mapper.BasketballMapper;
import test.util.BeanUtil;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Component
public class MongoPipeline implements Pipeline {

    private static BasketballMapper basketballMapper;

    @Autowired
    public void setBasketballMapper(BasketballMapper basketballMapper) {
        MongoPipeline.basketballMapper = basketballMapper;
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        Hoopster hoopster = resultItems.get("hoopster");
        basketballMapper.inset(hoopster);
    }

}
