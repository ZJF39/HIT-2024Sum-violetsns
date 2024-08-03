package cn.edu.hit.violetsns.Service;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;

public interface AdminActivityService {
    PageBean getAdminAllActivity(Integer page, Integer pageSize, String nickname, String title, String content, String status, String sortBy, String order);

    PageBean getAdminActivityComment(Integer page, Integer pageSize, String nickname, String content, String status, String sortBy, String order);

    void updateAdminActivityStatus(Integer activityId, String status);
}
