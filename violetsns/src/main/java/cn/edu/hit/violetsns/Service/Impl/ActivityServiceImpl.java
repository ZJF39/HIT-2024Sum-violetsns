package cn.edu.hit.violetsns.Service.Impl;

import cn.edu.hit.violetsns.Entity.pojo.Activity;
import cn.edu.hit.violetsns.Entity.pojo.ActivityJoin;
import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.PostElement;
import cn.edu.hit.violetsns.Entity.vo.Result;
import cn.edu.hit.violetsns.Mapper.ActivityMapper;
import cn.edu.hit.violetsns.Service.ActivityService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public PageBean getActivities(Integer page, Integer pageSize, String keyword, Integer userId) {
        PageHelper.startPage(page, pageSize);

        //2.查询
        List<PostElement> list = activityMapper.getActivities(keyword,userId);

        Page<PostElement> P = (Page<PostElement>) list;
        //3.封装
        PageBean pageBean = new PageBean(P.getTotal(), P.getResult());
        return pageBean;
    }

    @Override
    public Activity getActivitieById(Integer id) {
        Activity activity =activityMapper.getActivitieById(id);
        activityMapper.activityViewPlus(id);
        return activity;
    }

    @Override
    public void insertActivity(Activity activity) {
        activityMapper.insertActivity(activity);
    }

    @Override
    public void joinActivity(ActivityJoin activityJoin) {
        activityMapper.joinActivity(activityJoin);
    }


}
