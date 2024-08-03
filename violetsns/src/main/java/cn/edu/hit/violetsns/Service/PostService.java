package cn.edu.hit.violetsns.Service;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.pojo.Post;
import cn.edu.hit.violetsns.Entity.vo.PostComment;
import cn.edu.hit.violetsns.Entity.vo.PostDetail;
import cn.edu.hit.violetsns.Entity.vo.PostElement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public interface PostService {
    void insert(Post post);


    PageBean getPosts(Integer page, Integer pageSize, String keyword,Integer uid);

    PostDetail findById(Integer PostId, Integer page, Integer pageSize);

    void thumbUp(Integer postId);

    void insertComment(PostComment postComment);
}
