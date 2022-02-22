package top.banner.lib.robot;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.banner.lib.robot.core.DingtalkProperties;
import top.banner.lib.robot.core.Sender;
import top.banner.lib.robot.core.entity.LinkMessage;
import top.banner.lib.robot.core.entity.MarkdownMessage;
import top.banner.lib.robot.core.entity.Message;
import top.banner.lib.robot.core.entity.TextMessage;
import top.banner.lib.robot.core.task.SendTask;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class DingtalkSpringBootStarterApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(DingtalkSpringBootStarterApplicationTests.class);

    @Resource
    private DingtalkProperties dingtalkProperties;
    @Resource
    private Sender dingtalkRobot;
    @Resource
    private ConcurrentLinkedQueue<SendTask> sendTaskQueue;

    @Resource
    private Message.At at;

    @Test
    public void dingtalkProperties() {
        log.info("webhook ==> {}", dingtalkProperties.getWebhook());
    }


    @Test
    public void text() {
        dingtalkRobot.send(new TextMessage("测试 你好", at));
        waiting();
    }


    @Test
    public void link() {
        dingtalkRobot.send(new LinkMessage(new LinkMessage.Link("标题", "测试 内容", "https://open.dingtalk.com/document/robots/custom-robot-access")));
        waiting();
    }

    @Test
    public void markdown() {
        dingtalkRobot.send(new MarkdownMessage(new MarkdownMessage.Markdown("杭州天气1", " ### 测试1 \n #### 杭州天气 @150XXXXXXXX \n > 9度，西北风1级，空气良89，相对温度73%\n > ![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png)\n > ###### 10点20分发布 [天气](https://www.dingtalk.com) \n"), at));
        dingtalkRobot.send(new MarkdownMessage(new MarkdownMessage.Markdown("杭州天气2", " ### 测试2 \n #### 杭州天气 @150XXXXXXXX \n > 9度，西北风1级，空气良89，相对温度73%\n > ![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png)\n > ###### 10点20分发布 [天气](https://www.dingtalk.com) \n"), at));
        dingtalkRobot.send(new MarkdownMessage(new MarkdownMessage.Markdown("杭州天气3", " ### 测试3 \n #### 杭州天气 @150XXXXXXXX \n > 9度，西北风1级，空气良89，相对温度73%\n > ![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png)\n > ###### 10点20分发布 [天气](https://www.dingtalk.com) \n"), at));
        dingtalkRobot.send(new MarkdownMessage(new MarkdownMessage.Markdown("杭州天气4", " ### 测试4 \n #### 杭州天气 @150XXXXXXXX \n > 9度，西北风1级，空气良89，相对温度73%\n > ![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png)\n > ###### 10点20分发布 [天气](https://www.dingtalk.com) \n"), at));
        dingtalkRobot.send(new MarkdownMessage(new MarkdownMessage.Markdown("杭州天气5", " ### 测试5 \n #### 杭州天气 @150XXXXXXXX \n > 9度，西北风1级，空气良89，相对温度73%\n > ![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png)\n > ###### 10点20分发布 [天气](https://www.dingtalk.com) \n"), at));
        waiting();
    }

    public void waiting() {
        while (!sendTaskQueue.isEmpty()) {
            sleep(2);
        }
        log.info("queue is empty");
    }

    public void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
