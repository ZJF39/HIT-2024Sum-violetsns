package cn.edu.hit.violetsns.Service;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;

public interface AdminCommentService {
    PageBean getAdminAllComments(Integer page, Integer pageSize,Integer postId,Integer uid, String nickname, String content, String status, String sortBy, String order);

    void updateCommentStatus(Integer commentId, String status);
}
