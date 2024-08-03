package cn.edu.hit.violetsns.Mapper;

import cn.edu.hit.violetsns.Entity.pojo.JobCV;
import cn.edu.hit.violetsns.Entity.pojo.SysUser;
import cn.edu.hit.violetsns.Entity.pojo.UserDetails;
import cn.edu.hit.violetsns.Entity.vo.PostElement;
import cn.edu.hit.violetsns.Entity.vo.UserProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserCenterMapper {
    UserProfile getUserInfo(Integer userId);

    void updateSysUser(Integer userId, UserProfile userProfile);

    void updateUserDetails(Integer userId, UserProfile userProfile);

    Integer getTotalThumbsByUserId(Integer userId);

    Integer getTotalCommentsByUserId(Integer userId);
    Integer getTotalPostsByUserId(Integer userId);

    List<PostElement> getMyPosts(Integer userId);

    List<PostElement> getMyJobs(Integer userId);

    List<JobCV> getMyResumes(Integer userId);

    List<PostElement> getMyResources(Integer userId);
}
