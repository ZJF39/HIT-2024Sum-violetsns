package cn.edu.hit.violetsns.Mapper;

import cn.edu.hit.violetsns.Entity.pojo.Activity;
import cn.edu.hit.violetsns.Entity.pojo.ActivityJoin;
import cn.edu.hit.violetsns.Entity.vo.PostElement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityMapper {

    List<PostElement> getActivities(String keyword, Integer userId);

    Activity getActivitieById(Integer id);

    void activityViewPlus(Integer id);

    void insertActivity(Activity activity);

    void joinActivity(ActivityJoin activityJoin);
}
