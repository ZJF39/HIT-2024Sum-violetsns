package cn.edu.hit.violetsns.Controller.Admin;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.Result;
import cn.edu.hit.violetsns.Service.AdminCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/comments")
@Api(tags = "管理员评论管理模块")
public class AdminCommentController {
    @Autowired
    private AdminCommentService adminCommentService;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('sys:admin')")
    @ApiOperation("获取所有评论信息")
    public Result getAdminAllComments(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      Integer postId,Integer uid, String nickname, String content, String status, String sortBy, String order) {
        PageBean pageBean = adminCommentService.getAdminAllComments(page, pageSize, postId,uid, nickname, content, status, sortBy, order);
        return Result.succ(pageBean);
    }

    @PutMapping("")
    @ApiOperation("更新博文评论状态")
    @PreAuthorize("hasAnyAuthority('sys:admin')")
    public Result updateUserStatus(@RequestParam Integer commentId,
                                   @RequestParam String status) {
        adminCommentService.updateCommentStatus(commentId, status);
        return Result.succ("评论状态修改成功");
    }


}
