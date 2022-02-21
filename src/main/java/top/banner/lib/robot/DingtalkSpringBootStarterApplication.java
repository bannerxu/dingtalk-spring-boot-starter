package top.banner.lib.robot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import top.banner.lib.robot.core.DingtalkProperties;

@SpringBootApplication
public class DingtalkSpringBootStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DingtalkSpringBootStarterApplication.class, args);
    }

}
