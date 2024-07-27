package cn.edu.hit.violetsns.Service;

import cn.edu.hit.violetsns.Entity.pojo.UserDetails;
import cn.edu.hit.violetsns.Entity.vo.UserProfile;

public interface UserCenterService {
    UserProfile getUserInfo(Integer userId);

    void updateUserInfo(Integer userId, UserProfile userProfile);
}
