package cn.edu.hit.violetsns.Service;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.AdminPostDetail;

public interface AdminPostService {
    PageBean getAdminAllPosts(Integer page, Integer pageSize, Integer postId, String username, String nickname, String title, String content,String status, String sortBy, String order);

    void updatePostStatus(Integer postId, String status);
}
