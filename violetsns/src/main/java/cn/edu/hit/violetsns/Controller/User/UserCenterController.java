package cn.edu.hit.violetsns.Controller.User;

import cn.edu.hit.violetsns.Entity.pojo.AccountUser;
import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.pojo.UserDetails;
import cn.edu.hit.violetsns.Entity.vo.Result;
import cn.edu.hit.violetsns.Entity.vo.UserProfile;
import cn.edu.hit.violetsns.Service.UserCenterService;
import cn.edu.hit.violetsns.Utils.RedisCache;
import com.aliyun.oss.AliOSSUtils;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
@Api(tags = "个人中心")
public class UserCenterController {

    @Autowired
    private UserCenterService userCenterService;
    @Autowired
    RedisCache redisCache;

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result getUserInfo(Integer userId){
        if(userId==null){
            AccountUser user = (AccountUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userId = user.getUserId();
        }
        return Result.succ(userCenterService.getUserInfo(userId));
    }

    @PutMapping("/info")
    @ApiOperation("更新用户信息")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Nickname", value = "用户昵称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "Avatar", value = "用户头像", required = true, dataType = "String"),
            @ApiImplicitParam(name = "gender", value = "用户性别", required = true, dataType = "String"),
            @ApiImplicitParam(name = "address", value = "用户地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "用户邮箱", required = true, dataType = "String"),
            @ApiImplicitParam(name = "mobile", value = "用户手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "school", value = "用户学校", required = true, dataType = "String"),
            @ApiImplicitParam(name = "major", value = "用户专业", required = true, dataType = "String")
    })
    public Result updateUserInfo(@RequestBody UserProfile userProfile){
        AccountUser user = (AccountUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = user.getUserId();
        userProfile.setUserId(userId);
        userCenterService.updateUserInfo(userId,userProfile);
        return Result.succ("更新成功");
    }

    @PostMapping("/upload")
    @ApiOperation("上传头像图片")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "MultipartFile")
    })
    public Result uploadAvatar(MultipartFile file) throws Exception {
        String url = aliOSSUtils.upload(file);
        return Result.succ(url);
    }

    @GetMapping("/posts")
    @ApiOperation("获取我发布的博文")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result getMyPosts(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer pageSize){
        AccountUser user = (AccountUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = user.getUserId();
        PageBean pageBean =userCenterService.getMyPosts(userId,page,pageSize);
        return Result.succ(pageBean);
    }


    @GetMapping("/jobs")
    @ApiOperation("获取我发布的内推")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result getMyJobs(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer pageSize){
        AccountUser user = (AccountUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = user.getUserId();
        PageBean pageBean = userCenterService.getMyJobs(userId,page,pageSize);
        return Result.succ(pageBean);
    }

    @GetMapping("/resources")
    @ApiOperation("获取我发布的资源")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result getMyResources(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer pageSize){
        AccountUser user = (AccountUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = user.getUserId();
        PageBean pageBean = userCenterService.getMyResources(userId,page,pageSize);
        return Result.succ(pageBean);
    }

    @GetMapping("/resumes")
    @ApiOperation("获取我发布的简历")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result getMyResumes(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize){
        AccountUser user = (AccountUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = user.getUserId();
        PageBean pageBean = userCenterService.getMyResumes(userId,page,pageSize);
        return Result.succ(pageBean);
    }






}
