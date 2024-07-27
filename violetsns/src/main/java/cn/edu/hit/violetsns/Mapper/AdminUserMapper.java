package cn.edu.hit.violetsns.Mapper;

import cn.edu.hit.violetsns.Entity.pojo.SysUser;
import cn.edu.hit.violetsns.Entity.pojo.UserDetails;
import cn.edu.hit.violetsns.Entity.vo.CountTMF;
import cn.edu.hit.violetsns.Entity.vo.DailyUserCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminUserMapper {
    List<SysUser> getUsersInfo(String username, String nickname);

    SysUser getUserById(Integer userId);

    UserDetails getUserDetails(Integer userId);

    void updateUserStatus(Integer userId, String status);

    CountTMF count();

    List<DailyUserCount> count30days();
}
