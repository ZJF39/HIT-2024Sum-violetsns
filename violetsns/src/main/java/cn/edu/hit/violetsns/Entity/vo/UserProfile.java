package cn.edu.hit.violetsns.Entity.vo;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private Integer userId;
    private String nickname;
    private String avatar;
    private String gender;
    private String mobile;
    private String email;
    private String school;
    private String major;
    private String address;
    @JsonProperty("nUpvote")
    // 获赞数
    private Integer nUpvote;
    // 发帖数
    @JsonProperty("nPost")
    private Integer nPost;
    // 评论数
    @JsonProperty("nComment")
    private Integer nComment;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
