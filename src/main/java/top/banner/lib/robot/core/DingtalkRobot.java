package top.banner.lib.robot.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;
import top.banner.lib.robot.constant.DingtalkConstant;
import top.banner.lib.robot.core.entity.Message;
import top.banner.lib.robot.core.task.SendTask;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DingtalkRobot implements Sender {
    private static final Logger log = LoggerFactory.getLogger(DingtalkRobot.class);

    private final DingtalkProperties dingtalkProperties;
    private final ConcurrentLinkedQueue<SendTask> sendTaskQueue;

    public DingtalkRobot(DingtalkProperties dingtalkProperties, ConcurrentLinkedQueue<SendTask> sendTaskQueue) {
        this.dingtalkProperties = dingtalkProperties;
        this.sendTaskQueue = sendTaskQueue;
    }


    @Resource
    @Qualifier(DingtalkConstant.DINGTALK_REST_TEMPLATE)
    private RestTemplate restTemplate;

    public void send(Message message) {
        sendTaskQueue.add(new SendTask(dingtalkProperties, restTemplate, message));
    }


}
