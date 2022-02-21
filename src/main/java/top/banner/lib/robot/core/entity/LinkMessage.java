package top.banner.lib.robot.core.entity;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LinkMessage extends Message {
    public String getMsgtype() {
        return "link";
    }

    private Link link;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Link implements Serializable {
        private String title;
        private String text;
        private String messageUrl;
        private String picUrl;


        public Link(String title, String text, String messageUrl) {
            this.title = title;
            this.text = text;
            this.messageUrl = messageUrl;
        }
    }
}
