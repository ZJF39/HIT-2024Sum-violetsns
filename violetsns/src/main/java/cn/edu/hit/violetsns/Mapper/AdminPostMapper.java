package cn.edu.hit.violetsns.Mapper;

import cn.edu.hit.violetsns.Entity.vo.AdminPostDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminPostMapper {

    List<AdminPostDetail> getAdminAllPosts(Integer postId, String username, String nickname, String title, String content,String status, String sortBy, String order);

    void updatePostStatus(Integer postId, String status);
}
