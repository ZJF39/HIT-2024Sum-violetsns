package cn.edu.hit.violetsns.Entity.vo;

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
public class JobDetail {
    private Integer jobId;
    private String nickname;
    private String title;
    private String content;
    @JsonProperty("nBrowse")
    private Integer nBrowse;
    @JsonProperty("nUpvote")
    private Integer nUpvote;
    private String avatar;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    private String status;
}
