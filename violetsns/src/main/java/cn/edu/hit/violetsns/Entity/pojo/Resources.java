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
public class Resources {
    private Integer id;
    private Integer uid;
    private String nickname;
    private String title;
    private String content;
    private String resource;
    @JsonProperty("nBrowse")
    private Integer nBrowse;
    @JsonProperty("nUpvote")
    private Integer nUpvote;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private String status;

}
