package cn.edu.hit.violetsns.Controller.User;

import cn.edu.hit.violetsns.Entity.pojo.AccountUser;
import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.pojo.Resources;
import cn.edu.hit.violetsns.Entity.vo.Result;
import cn.edu.hit.violetsns.Service.ResourceService;
import com.aliyun.oss.AliOSSUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/api/resourse")
@Api(tags = "用户资源管理模块")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @GetMapping("")
    @ApiOperation("查看所有资源")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "keyword", value = "搜索关键字", required = false, dataType = "String")
    })
    public Result getAllResources(@RequestParam(defaultValue = "1")Integer page,
                                  @RequestParam(defaultValue = "10")Integer pageSize,
                                  String keyword){
        PageBean pageBean = resourceService.getAllResources(page, pageSize,keyword);
        return Result.succ(pageBean);
    }

    @GetMapping("/{id}")
    @ApiOperation("查看资源详情")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "资源id", required = true, dataType = "Integer")
    })
    public Result getResourceInfo(@ApiParam("资源id") @PathVariable Integer id){
        Resources resources = resourceService.getResourceInfo(id);
        return Result.succ(resources);
    }

    @PostMapping("")
    @ApiOperation("发布资源")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "资源标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "资源内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "resource", value = "资源文件url链接", required = true, dataType = "String")
    })
    public Result postResource(@RequestBody Resources resources) throws IOException {
        AccountUser user = (AccountUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = user.getUserId();
        resources.setUid(userId);
        resourceService.postResource(resources);
        return Result.succ("发布资源成功");
    }

    @PostMapping("/upload")
    @ApiOperation("上传资源")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "MultipartFile")
    })
    public Result uploadFile(MultipartFile file) throws IOException {
        String url = aliOSSUtils.upload(file);
        return Result.succ(url);
    }

    @ApiOperation("下载资源")
    @GetMapping("/download/{id}")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "资源id", required = true, dataType = "Integer")
    })
    public Result downloadResource(@PathVariable Integer id){
        String url = resourceService.downloadResource(id);
        return Result.succ(url);
    }


    @PutMapping("/{id}")
    @ApiOperation("点赞资源")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "资源id", required = true, dataType = "Integer")
    })
    public Result upvoteResourcePlus(@PathVariable Integer id){
        resourceService.ResourceUpvotePlus(id);
        return Result.succ("点赞成功");
    }
}
