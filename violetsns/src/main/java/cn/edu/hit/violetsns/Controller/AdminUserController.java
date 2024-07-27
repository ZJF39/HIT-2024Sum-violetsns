package cn.edu.hit.violetsns.Controller;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.AdminUserDetail;
import cn.edu.hit.violetsns.Entity.vo.CountTMF;
import cn.edu.hit.violetsns.Entity.vo.DailyUserCount;
import cn.edu.hit.violetsns.Entity.vo.Result;
import cn.edu.hit.violetsns.Service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAnyAuthority('sys:admin')")
@RequestMapping("/api/admin/users")
@Api(tags = "管理员用户管理模块")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("")
    @ApiOperation("获取用户信息")
    @PreAuthorize("hasAnyAuthority('sys:admin')")
    public Result getUsersInfo(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer pageSize,
                               String username, String nickname) {


        PageBean pageBean = adminUserService.getUsersInfo(page, pageSize, username,nickname);
        return Result.succ(pageBean);
    }
    @GetMapping("/{id}")
    @ApiOperation("获取用户详细信息")
    @PreAuthorize("hasAnyAuthority('sys:admin')")
    public Result getUsersDetail(@PathVariable Integer id) {
        AdminUserDetail adminUserDetail = adminUserService.getUsersDetail(id);
        return Result.succ(adminUserDetail);
    }

    @PutMapping("")
    @ApiOperation("更新用户状态")
    @PreAuthorize("hasAnyAuthority('sys:admin')")
    public Result updateUserStatus(@RequestParam Integer userId,
                                   @RequestParam String status) {
        adminUserService.updateUserStatus(userId, status);
        return Result.succ("用户状态修改成功");
    }

    @GetMapping("/sum/total")
    @ApiOperation("按男女统计用户总数 总数 未知 男生 女生")
    @PreAuthorize("hasAnyAuthority('sys:admin')")
    public Result count() {
        CountTMF countTMF = adminUserService.count();
        return Result.succ(countTMF);
    }

    @GetMapping("/sum/daily")
    @ApiOperation("按日期统计用户总数")
    @PreAuthorize("hasAnyAuthority('sys:admin')")
    public Result count30days(){
        List<DailyUserCount> list = adminUserService.count30days();
        return Result.succ(list);
    }

}
