package cn.edu.hit.violetsns.Service;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.AdminUserDetail;
import cn.edu.hit.violetsns.Entity.vo.CountTMF;
import cn.edu.hit.violetsns.Entity.vo.DailyUserCount;

import java.util.List;

public interface AdminUserService {
    PageBean getUsersInfo(Integer page, Integer pageSize, String username, String nickname);

    AdminUserDetail getUsersDetail(Integer userId);

    void updateUserStatus(Integer userId, String status);

    CountTMF count();

    List<DailyUserCount> count30days();
}
