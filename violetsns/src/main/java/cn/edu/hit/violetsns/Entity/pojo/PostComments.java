package cn.edu.hit.violetsns.Entity.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostComments {
    private Integer pCommentId;
    private Integer pid;
    private Integer postId;
    private Integer uid;
    private Integer toUid;
    private String content;
    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    public void setpCommentId(Integer pCommentId) {
        this.pCommentId = pCommentId;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setToUid(Integer toUid) {
        this.toUid = toUid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
