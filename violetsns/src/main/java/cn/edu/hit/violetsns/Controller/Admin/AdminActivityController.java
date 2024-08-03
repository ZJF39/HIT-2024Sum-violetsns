package cn.edu.hit.violetsns.Controller.Admin;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.Result;
import cn.edu.hit.violetsns.Service.AdminActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/activity")
@Api(tags = "管理员活动管理模块")
public class AdminActivityController {

    @Autowired
    private AdminActivityService adminActivityService;

    @GetMapping("")
    @ApiOperation("获取所有活动信息")
    @PreAuthorize("hasAnyAuthority('sys:admin')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "nickname", value = "活动发布者昵称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "title", value = "活动标题", required = false, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "活动内容", required = false, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "活动状态", required = false, dataType = "String"),
            @ApiImplicitParam(name = "sortBy", value = "排序字段", required = false, dataType = "String"),
            @ApiImplicitParam(name = "order", value = "排序顺序", required = false, dataType = "String")
    })
    public Result getAdminActivity(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   String nickname,
                                   String title,
                                   String content,
                                   String status,
                                   String sortBy,
                                   String order) {
        PageBean pageBean =adminActivityService.getAdminAllActivity(page,pageSize,nickname,title,content,status,sortBy,order);
        return Result.succ(pageBean);

    }


    @GetMapping("/comments")
    @ApiOperation("获取活动评论信息")
    @PreAuthorize("hasAnyAuthority('sys:admin')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "nickname", value = "评论发布者昵称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "评论内容", required = false, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "评论状态", required = false, dataType = "String"),
            @ApiImplicitParam(name = "sortBy", value = "排序字段", required = false, dataType = "String"),
            @ApiImplicitParam(name = "order", value = "排序顺序", required = false, dataType = "String")
    })
    public Result getAdminActivityComment(@RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                          String nickname,
                                          String content,
                                          String status,
                                          String sortBy,
                                          String order){
        PageBean pageBean =adminActivityService.getAdminActivityComment(page,pageSize,nickname,content,status,sortBy,order);
        return Result.succ(pageBean);
    }

    @PutMapping("")
    @ApiOperation("更新活动状态")
    @PreAuthorize("hasAnyAuthority('sys:admin')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "活动id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "活动状态", required = true, dataType = "String")
    })
    public Result updateAdminActivityStatus(@RequestParam Integer activityId,
                                            @RequestParam String status){
        adminActivityService.updateAdminActivityStatus(activityId,status);
        return Result.succ("活动状态更新成功");
    }

}
