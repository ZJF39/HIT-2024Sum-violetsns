package cn.edu.hit.violetsns.Mapper;

import cn.edu.hit.violetsns.Entity.pojo.Post;
import cn.edu.hit.violetsns.Entity.vo.PostComment;
import cn.edu.hit.violetsns.Entity.vo.PostDetail;
import cn.edu.hit.violetsns.Entity.vo.PostElement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    Integer insert(Post post);

    Integer delete(Integer postId);

    List<PostElement> getPosts(String keyword,Integer uid);

    PostDetail findByPostId(Integer postId);


    void postViewPlus(Integer postId);

    void thumbUp(Integer postId);

    void insertComment(PostComment postComment);
}
