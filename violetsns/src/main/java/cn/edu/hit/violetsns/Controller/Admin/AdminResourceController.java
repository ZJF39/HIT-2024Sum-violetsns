package cn.edu.hit.violetsns.Controller.Admin;


import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.Result;
import cn.edu.hit.violetsns.Service.AdminResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/admin/resources")
@Api(tags = "管理员资源模块")
public class AdminResourceController {

    @Autowired
    private AdminResourceService adminResourceService;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('sys:admin')")
    @ApiOperation("分页查找所有的资源信息，可根据username，title，content模糊查找")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "username", value = "用户名", required = false, dataType = "String"),
            @ApiImplicitParam(name = "title", value = "资源标题", required = false, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "资源内容", required = false, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "资源状态", required = false, dataType = "String"),
            @ApiImplicitParam(name = "sort_by", value = "排序字段", required = false, dataType = "String"),
            @ApiImplicitParam(name = "order", value = "排序方式", required = false, dataType = "String")
    })
    public Result getAllResource(@RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                 String username, String title, String content, Integer status, String sort_by, String order){
        PageBean pageBean = adminResourceService.getResourceDetail(page, pageSize, username, title, content, status, sort_by, order);
        return Result.succ(pageBean);
    }

    @PutMapping("")
    @PreAuthorize("hasAnyAuthority('sys:admin')")
    @ApiOperation("根据id和设置status，删除某个资源")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "资源id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "资源状态", required = true, dataType = "String")
    })
    public Result delete(@RequestParam Integer id, @RequestParam String status ){
        if(Objects.equals(status, "删除")){
            adminResourceService.delete(id);
            return Result.succ("资源下架成功");
        }
        return Result.succ("无效操作");
    }

}
