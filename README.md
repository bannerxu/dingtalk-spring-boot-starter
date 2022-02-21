# 工程简介

通过简单配置，完成钉钉机器人消息发送

# 如何接入

```xml

<dependency>
    <groupId>top.banner.lib</groupId>
    <artifactId>dingtalk-spring-boot-starter</artifactId>
    <version>0.0.4-SNAPSHOT</version>
</dependency>
```

```java

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1ApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(Demo1ApplicationTests.class);
    @Resource
    private Sender dingtalkRobot;
    @Resource
    private ConcurrentLinkedQueue<SendTask> sendTaskQueue;
    @Resource
    private Message.At at;

    @Test
    public void contextLoads() {
        dingtalkRobot.send(new MarkdownMessage(new MarkdownMessage.Markdown("杭州天气1", " ### 测试1 \n #### 杭州天气 @150XXXXXXXX \n > 9度，西北风1级，空气良89，相对温度73%\n > ![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png)\n > ###### 10点20分发布 [天气](https://www.dingtalk.com) \n"), at));
        dingtalkRobot.send(new MarkdownMessage(new MarkdownMessage.Markdown("杭州天气2", " ### 测试2 \n #### 杭州天气 @150XXXXXXXX \n > 9度，西北风1级，空气良89，相对温度73%\n > ![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png)\n > ###### 10点20分发布 [天气](https://www.dingtalk.com) \n"), at));
        dingtalkRobot.send(new MarkdownMessage(new MarkdownMessage.Markdown("杭州天气3", " ### 测试3 \n #### 杭州天气 @150XXXXXXXX \n > 9度，西北风1级，空气良89，相对温度73%\n > ![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png)\n > ###### 10点20分发布 [天气](https://www.dingtalk.com) \n"), at));
        dingtalkRobot.send(new MarkdownMessage(new MarkdownMessage.Markdown("杭州天气4", " ### 测试4 \n #### 杭州天气 @150XXXXXXXX \n > 9度，西北风1级，空气良89，相对温度73%\n > ![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png)\n > ###### 10点20分发布 [天气](https://www.dingtalk.com) \n"), at));
        dingtalkRobot.send(new MarkdownMessage(new MarkdownMessage.Markdown("杭州天气5", " ### 测试5 \n #### 杭州天气 @150XXXXXXXX \n > 9度，西北风1级，空气良89，相对温度73%\n > ![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png)\n > ###### 10点20分发布 [天气](https://www.dingtalk.com) \n"), at));
        waiting();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
```
