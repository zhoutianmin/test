package test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import test.service.DinshiService;

@Component
public class Dinshi {
    @Autowired
    private DinshiService dinshiService;


    @Scheduled(cron = "*/9 * * * * ?")//五秒一执行
    public void task(){
        dinshiService.Tasktest();
    }

}
