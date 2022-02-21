package top.banner.lib.robot.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TextMessage extends Message {
    public String getMsgtype() {
        return "text";
    }

    private Text text;

    private At at;

    public TextMessage(String text, At at) {
        this.text = new Text(text);
        this.at = at;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Text implements Serializable {
        private String content;
    }
}
