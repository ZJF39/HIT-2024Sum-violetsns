package cn.edu.hit.violetsns.Controller.User;


import cn.edu.hit.violetsns.Entity.pojo.AccountUser;
import cn.edu.hit.violetsns.Entity.pojo.Activity;
import cn.edu.hit.violetsns.Entity.pojo.ActivityJoin;
import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.Result;
import cn.edu.hit.violetsns.Service.ActivityService;
import com.aliyun.oss.AliOSSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/activities")
@Api(tags = "活动模块")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @GetMapping("")
    @ApiOperation("查询活动(关键字检索、用户id检索)")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result getActivities(@RequestParam(defaultValue = "1")Integer page,
                                @RequestParam(defaultValue = "10")Integer pageSize,
                                String keyword,Integer userId){
        PageBean pageBean = activityService.getActivities(page,pageSize,keyword,userId);
        return Result.succ(pageBean);
    }

    @GetMapping("/{id}")
    @ApiOperation("展示详情页")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result getActivityById(@PathVariable Integer id){
        Activity activity =activityService.getActivitieById(id);
        return Result.succ(activity);
    }

    @PostMapping("")
    @ApiOperation("添加活动")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result insertActivity(@RequestBody Activity activity){
        if(activity.getCoverPicture()==null){
            activity.setCoverPicture("/default-image");
        }
        AccountUser user = (AccountUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = user.getUserId();
        activity.setUid(userId);
        activityService.insertActivity(activity);
        return Result.succ("发布活动成功");
    }

    @PostMapping("/{id}")
    @ApiOperation("参加活动")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result joinActivity(@PathVariable("id")Integer activityId){
        AccountUser user = (AccountUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = user.getUserId();
        ActivityJoin activityJoin = new ActivityJoin();
        activityJoin.setUid(userId);
        activityJoin.setActivityId(activityId);
        activityService.joinActivity(activityJoin);
        return Result.succ("参加活动成功");
    }


}
