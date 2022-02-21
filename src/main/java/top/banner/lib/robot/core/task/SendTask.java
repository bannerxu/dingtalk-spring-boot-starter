package top.banner.lib.robot.core.task;

import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import top.banner.lib.robot.core.DingtalkProperties;
import top.banner.lib.robot.core.entity.Message;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class SendTask {
    private static final Logger log = LoggerFactory.getLogger(SendTask.class);

    private final DingtalkProperties dingtalkProperties;
    private final RestTemplate restTemplate;
    private final Message message;


    public SendTask(DingtalkProperties dingtalkProperties, RestTemplate restTemplate, Message message) {
        this.dingtalkProperties = dingtalkProperties;
        this.restTemplate = restTemplate;
        this.message = message;
    }


    public void send() {
        String webhook = dingtalkProperties.getWebhook();
        try {
            Date date = new Date();
            log.info("开始发送消息：{}", date);
            String sign = sign(date.getTime());
            ResponseEntity<String> entity;
            if (sign == null) {
                entity = restTemplate.postForEntity(webhook, message, String.class);
            } else {
                entity = restTemplate.postForEntity(webhook + "&timestamp=" + date.getTime() + "&sign=" + sign, message, String.class);
            }

            log.info("<===== success code {} body {}", entity.getStatusCodeValue(), entity.getBody());
        } catch (Exception e) {
            log.error("<=====error=====>", e);
        }
    }


    @SneakyThrows
    public String sign(Long timestamp) {
        String secret = dingtalkProperties.getSecret();
        if (StringUtils.isBlank(secret)) {
            return null;
        }
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
        return URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
    }
}
