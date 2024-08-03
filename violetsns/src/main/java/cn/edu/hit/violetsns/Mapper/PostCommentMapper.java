package cn.edu.hit.violetsns.Mapper;

import cn.edu.hit.violetsns.Entity.pojo.PostComments;
import cn.edu.hit.violetsns.Entity.vo.PostDetail;
import cn.edu.hit.violetsns.Entity.vo.VOPostComments;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface PostCommentMapper {
    List<VOPostComments> findByPostId(Integer postId);
    Integer delete(Integer id);
    Integer insert(PostComments postComments);
    Integer countByPostId(Integer postId);

}
