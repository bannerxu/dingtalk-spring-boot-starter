package top.banner.lib.robot.core;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;
import top.banner.lib.robot.constant.DingtalkConstant;
import top.banner.lib.robot.core.entity.Message;
import top.banner.lib.robot.core.task.SendTask;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DingtalkRobot implements Sender {

    private final ConcurrentLinkedQueue<SendTask> sendTaskQueue;

    @Resource
    private DingtalkProperties dingtalkProperties;


    @Resource
    @Qualifier(DingtalkConstant.DINGTALK_REST_TEMPLATE)
    private RestTemplate restTemplate;

    public DingtalkRobot(ConcurrentLinkedQueue<SendTask> sendTaskQueue) {
        this.sendTaskQueue = sendTaskQueue;
    }

    public void send(Message message) {
        if (sendTaskQueue.size() > dingtalkProperties.getMaxQueueSize()) {
            throw new IllegalArgumentException("Queue exceeds maximum");
        }
        sendTaskQueue.add(new SendTask(dingtalkProperties, restTemplate, message));
    }


}
