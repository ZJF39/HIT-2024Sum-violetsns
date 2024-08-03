package cn.edu.hit.violetsns.Entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

public class VOPostComments {

    @JsonProperty("pCommentId")
    private String pCommentId;
    @JsonProperty("pid")
    private Integer pid;
    @JsonProperty("postId")
    private Integer postId;
    @JsonProperty("uid")
    private Integer uid;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("toUid")
    private String toUid;
    @JsonProperty("toNickname")
    private String toNickname;
    @JsonProperty("nUpvote")
    private Integer nUpvote;
    @JsonProperty("content")
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String createTime;

}
