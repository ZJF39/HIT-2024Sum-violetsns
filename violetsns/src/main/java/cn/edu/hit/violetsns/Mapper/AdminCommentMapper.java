package cn.edu.hit.violetsns.Mapper;

import cn.edu.hit.violetsns.Entity.vo.AdminCommentDetail;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminCommentMapper {


    List<AdminCommentDetail> getAdminAllComments(Integer postId,Integer uid, String nickname, String content, String status, String sortBy, String order);

    void updateCommentStatus(Integer commentId, String status);
}
