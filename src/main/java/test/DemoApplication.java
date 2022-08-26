package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import test.util.BeanUtil;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

    public static void main(String[] args) {
        //run方法的返回值ConfigurableApplicationContext继承了ApplicationContext上下文接口
        ConfigurableApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        //将run方法的返回值赋值给工具类中的静态变量
        BeanUtil.applicationContext = applicationContext;
        //测试获取已经实例化的接口bean，执行bean中方法
//        new Basketball().setBean();
    }

}
