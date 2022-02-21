package top.banner.lib.robot.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
public abstract class Message implements Serializable {


    public abstract String getMsgtype();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class At {
        private List<String> atMobiles;

        private Boolean isAtAll = false;
    }

}
