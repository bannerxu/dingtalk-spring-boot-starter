package top.banner.lib.robot.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import top.banner.lib.robot.core.DingtalkProperties;
import top.banner.lib.robot.core.DingtalkRobot;
import top.banner.lib.robot.core.entity.Message;
import top.banner.lib.robot.core.task.SendTask;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Configuration
@ConditionalOnClass(DingtalkRobot.class)
@EnableConfigurationProperties(DingtalkProperties.class)
@EnableScheduling
public class DingtalkConfiguration {


    @Resource
    private DingtalkProperties dingtalkProperties;
    @Resource
    private ConcurrentLinkedQueue<SendTask> sendTaskQueue;


    @Bean
    public DingtalkRobot dingtalkSender() {
        return new DingtalkRobot(dingtalkProperties, sendTaskQueue);
    }

    @Bean
    public Message.At at() {
        String atMobiles = dingtalkProperties.getAtMobiles();
        return Message.At.builder()
                .atMobiles(Arrays.stream(atMobiles.split(",")).collect(Collectors.toList()))
                .isAtAll(dingtalkProperties.getAtAll())
                .build();

    }

    @Bean
    public ConcurrentLinkedQueue<SendTask> sendTaskQueue() {
        return new ConcurrentLinkedQueue<>();
    }

    @Scheduled(cron = "*/3 * * * * ?")
    public void run() {
        SendTask task = sendTaskQueue.poll();
        if (task == null) {
            return;
        }
        task.send();
    }


}
