package cn.edu.hit.violetsns.Entity.vo;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDetail {
    private Integer postId;
    private Integer userId;
    private String title;

    @JsonProperty("nUpvote")
    private Integer nUpvote;

    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    private String nickname;
    private String avatar;
    private PageBean comments;
}
