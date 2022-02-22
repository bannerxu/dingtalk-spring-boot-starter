package top.banner.lib.robot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import top.banner.lib.robot.constant.DingtalkConstant;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
@ConditionalOnMissingBean(name = DingtalkConstant.DINGTALK_REST_TEMPLATE)
@ConfigurationProperties(prefix = DingtalkConstant.DINGTALK_PROPERTIES_PREFIX + "http-client")
public class DingtalkHttpClientConfig {

    /**
     * 连接超时时间
     */
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration connectTimeout = Duration.ofSeconds(30);
    /**
     * 读超时时间
     */
    @DurationUnit(ChronoUnit.SECONDS)
    private Duration readTimeout = Duration.ofSeconds(30);

    @Bean(name = DingtalkConstant.DINGTALK_REST_TEMPLATE)
    public RestTemplate restTemplate() {
        return new RestTemplate(dingtalkClientHttpRequestFactory());
    }

    public ClientHttpRequestFactory dingtalkClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout((int) readTimeout.toMillis());
        factory.setConnectTimeout((int) connectTimeout.toMillis());
        return factory;
    }

    public Duration getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Duration connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Duration getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Duration readTimeout) {
        this.readTimeout = readTimeout;
    }
}