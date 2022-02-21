package top.banner.lib.robot.core.entity;

import lombok.*;

import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MarkdownMessage extends Message {
    public String getMsgtype() {
        return "markdown";
    }

    private Markdown markdown;

    private At at;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Markdown implements Serializable {
        private String title;
        private String text;
    }
}
