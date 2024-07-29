package cn.edu.hit.violetsns.Controller.Admin;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.AdminPostDetail;
import cn.edu.hit.violetsns.Entity.vo.Result;
import cn.edu.hit.violetsns.Service.AdminPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/post")
@Api(tags = "管理员帖子管理模块")
public class AdminPostController {


    @Autowired
    private AdminPostService adminPostService;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('sys:admin')")
    @ApiOperation("获取所有帖子信息,可根据username、nickname、title、content模糊查询，根据postId精确查询，根据sortBy和order确认排序逻辑")
    public Result getAdminAllPosts(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   String username, String nickname,String title, String content,String sortBy,String order,Integer postId,String status){
        PageBean pageBean =adminPostService.getAdminAllPosts(page,pageSize,postId,username,nickname,title,content,status,sortBy,order);
        return Result.succ(pageBean);
    }

    @PutMapping("")
    @ApiOperation("更新博文状态")
    @PreAuthorize("hasAnyAuthority('sys:admin')")
    public Result updateUserStatus(@RequestParam Integer postId,
                                   @RequestParam String status) {
        adminPostService.updatePostStatus(postId, status);
        return Result.succ("博文状态修改成功");
    }

}
