package cn.edu.hit.violetsns.Entity.pojo;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class post {
    private Integer postId;
    private Integer uid;
    private String cateId;
    private String title;
    private String content;
    private Integer readCount;
    private String postTop;
    private Date createTime;
    private String status;
}
