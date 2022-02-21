package top.banner.lib.robot.core;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.dingtalk")
public class DingtalkProperties {
    private String webhook;

    private String atMobiles = "";

    private String keywords;

    private Boolean atAll = false;

    private String secret = "";


    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public String getAtMobiles() {
        return atMobiles;
    }

    public void setAtMobiles(String atMobiles) {
        this.atMobiles = atMobiles;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Boolean getAtAll() {
        return atAll;
    }

    public void setAtAll(Boolean atAll) {
        this.atAll = atAll;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
