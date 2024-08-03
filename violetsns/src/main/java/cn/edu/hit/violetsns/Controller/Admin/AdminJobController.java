package cn.edu.hit.violetsns.Controller.Admin;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.Result;
import cn.edu.hit.violetsns.Service.AdminJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/jobs")
@Api(tags = "管理员内推管理模块")
public class AdminJobController {

    @Autowired
    private AdminJobService adminJobService;

    @GetMapping("")
    @ApiOperation("获取所有内推信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "title", value = "标题", required = false, dataType = "String"),
            @ApiImplicitParam(name = "nickname", value = "发布者昵称", required = false, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, dataType = "String"),
            @ApiImplicitParam(name = "sortBy", value = "排序字段", required = false, dataType = "String"),
            @ApiImplicitParam(name = "order", value = "排序方式", required = false, dataType = "String")
    })
    public Result getAdminJobs(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               String title,String nickname,String status,String sortBy,String order){
        PageBean pageBean = adminJobService.getAdminJobs(page,pageSize,title,nickname,status,sortBy,order);
        return Result.succ(pageBean);
    }


    @PutMapping("")
    @ApiOperation("更新内推状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jobId", value = "内推id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "状态", required = true, dataType = "String")
    })
    public Result updateJobStatus(@RequestParam Integer jobId,@RequestParam String status){
        adminJobService.updateJobStatus(jobId,status);
        return Result.succ("内推状态更新成功");
    }
}
