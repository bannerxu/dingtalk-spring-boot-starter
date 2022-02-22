package top.banner.lib.robot.core;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    @Qualifier(DingtalkConstant.DINGTALK_REST_TEMPLATE)
    private RestTemplate restTemplate;

    public DingtalkRobot(ConcurrentLinkedQueue<SendTask> sendTaskQueue) {
        this.sendTaskQueue = sendTaskQueue;
    }

    public void send(Message message) {
        if (sendTaskQueue.size() > dingtalkProperties.getMaxQueueSize()) {
            throw new IllegalArgumentException("Queue exceeds maximum");
        }
        if (StringUtils.isBlank(dingtalkProperties.getWebhook())) {
            throw new IllegalArgumentException("WebHook is null");
        }
        sendTaskQueue.add(new SendTask(dingtalkProperties, restTemplate, message));
    }


}
