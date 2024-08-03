package cn.edu.hit.violetsns.Service;

import cn.edu.hit.violetsns.Entity.pojo.Activity;
import cn.edu.hit.violetsns.Entity.pojo.ActivityJoin;
import cn.edu.hit.violetsns.Entity.pojo.PageBean;

public interface ActivityService {
    PageBean getActivities(Integer page, Integer pageSize, String keyword, Integer userId);

    Activity getActivitieById(Integer id);

    void insertActivity(Activity activity);

    void joinActivity(ActivityJoin activityJoin);
}
