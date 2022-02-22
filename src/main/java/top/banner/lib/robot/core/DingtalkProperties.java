package top.banner.lib.robot.core;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ConfigurationProperties(prefix = "spring.dingtalk")
public class DingtalkProperties implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(DingtalkProperties.class);
    /**
     * 钉钉机器人的webhook
     */
    private String webhook;

    /**
     * 多个手机号用','分隔
     */
    private String atMobiles;

    /**
     * 多个关键词用','分隔
     */
    private String keywords;

    /**
     * 是否 @ 所有人
     */
    private Boolean atAll = false;

    /**
     * 配置签名 secret
     */
    private String secret;

    /**
     * 最多有 {maxQueueSize} 个待通知任务 默认最多2000
     */
    private int maxQueueSize = 2000;


    public String getWebhook() {
        return webhook;
    }

    /**
     * @param webhook 钉钉机器人的webhook
     */
    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public List<String> getAtMobiles() {
        if (StringUtils.isBlank(atMobiles) || Objects.equals("null", atMobiles)) {
            return new ArrayList<>();
        }
        return Arrays.asList(atMobiles.split(","));
    }

    /**
     * 通知的手机号
     *
     * @param atMobiles 多个手机号用','分隔
     */
    public void setAtMobiles(String atMobiles) {
        this.atMobiles = atMobiles;
    }

    public List<String> getKeywords() {
        if (StringUtils.isBlank(keywords) || Objects.equals("null", keywords)) {
            return new ArrayList<>();
        }
        return Arrays.asList(keywords.split(","));
    }

    /**
     * @param keywords 多个关键词用','分隔
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Boolean getAtAll() {
        return atAll;
    }

    /**
     * @param atAll 是否 @ 所有人
     */
    public void setAtAll(Boolean atAll) {
        this.atAll = atAll;
    }

    public String getSecret() {
        return secret;
    }

    /**
     * @param secret 配置签名 secret
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getMaxQueueSize() {
        return maxQueueSize;
    }

    /**
     * 最多有 {maxQueueSize} 个待通知任务
     */
    public void setMaxQueueSize(int maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }

    @Override
    public void afterPropertiesSet() {
        if (StringUtils.isBlank(webhook)) {
            log.error("未配置 机器人 webhook ，钉钉通知功能无法使用");
        }
    }
}
