package cn.edu.hit.violetsns.Controller.Admin;

import cn.edu.hit.violetsns.Entity.pojo.AccountUser;
import cn.edu.hit.violetsns.Entity.pojo.Notes;
import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.*;
import cn.edu.hit.violetsns.Service.AdminNotesService;
import cn.edu.hit.violetsns.Service.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/notes")
@Api(tags = "管理员公告管理模块")
public class AdminNotesController {

    @Autowired
    private AdminNotesService adminNotesService;

    @GetMapping("")
    @ApiOperation("查询公告")
    @PreAuthorize("hasAnyAuthority('sys:admin')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "title", value = "标题", required = false, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", required = false, dataType = "String"),
            @ApiImplicitParam(name = "sortBy", value = "排序字段", required = false, dataType = "String"),
            @ApiImplicitParam(name = "order", value = "排序方式", required = false, dataType = "String")
    })
    public Result getNotes(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           String title,String content,String sortBy,String order) {

        PageBean pageBean = adminNotesService.getNotes(page, pageSize, title,content,sortBy,order);
        return Result.succ(pageBean);
    }

    @PostMapping("")
    @ApiOperation("上传公告")
    @PreAuthorize("hasAnyAuthority('sys:admin')")
    public Result uploadNotes(@RequestBody Notes notes){
        AccountUser user = (AccountUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = user.getUserId();
        notes.setUserId(userId);
        adminNotesService.uploadNotes(notes);
        return Result.succ("发布公告成功");
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除公告")
    @PreAuthorize("hasAnyAuthority('sys:admin')")
    public Result deleteNotes(@PathVariable Integer id){
        adminNotesService.deleteNotes(id);
        return Result.succ("删除公告成功");
    }

}
