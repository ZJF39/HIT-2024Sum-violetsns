package cn.edu.hit.violetsns.Entity.pojo;

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
public class Activity {
    private Integer activityId;
    private Integer uid;
    private String nickname;
    private String title;
    private String content;
    private String coverPicture;
    @JsonProperty("nBrowse")
    private Integer readCount;
    @JsonProperty("nUpvote")
    private Integer thumbCount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

}
