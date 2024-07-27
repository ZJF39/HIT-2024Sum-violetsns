package cn.edu.hit.violetsns.Mapper;

import cn.edu.hit.violetsns.Entity.pojo.SysUser;
import cn.edu.hit.violetsns.Entity.pojo.UserDetails;
import cn.edu.hit.violetsns.Entity.vo.UserProfile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserCenterMapper {
    UserProfile getUserInfo(Integer userId);

    void updateSysUser(Integer userId, SysUser sysUser);

    void updateUserDetails(Integer userId, UserDetails userDetails);
}
