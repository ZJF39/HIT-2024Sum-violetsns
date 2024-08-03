package cn.edu.hit.violetsns.Service;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.pojo.UserDetails;
import cn.edu.hit.violetsns.Entity.vo.UserProfile;

public interface UserCenterService {
    UserProfile getUserInfo(Integer userId);

    void updateUserInfo(Integer userId, UserProfile userProfile);

    PageBean getMyPosts(Integer userId, Integer page, Integer pageSize);

    PageBean getMyJobs(Integer userId, Integer page, Integer pageSize);

    PageBean getMyResumes(Integer userId, Integer page, Integer pageSize);

    PageBean getMyResources(Integer userId, Integer page, Integer pageSize);
}
