package cn.edu.hit.violetsns.Controller;

import cn.edu.hit.violetsns.Entity.pojo.AccountUser;
import cn.edu.hit.violetsns.Entity.pojo.UserDetails;
import cn.edu.hit.violetsns.Entity.vo.Result;
import cn.edu.hit.violetsns.Entity.vo.UserProfile;
import cn.edu.hit.violetsns.Service.UserCenterService;
import cn.edu.hit.violetsns.Utils.RedisCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Api(tags = "个人中心")
public class UserCenterController {

    @Autowired
    private UserCenterService userCenterService;
    @Autowired
    RedisCache redisCache;

    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result getUserInfo(){
        AccountUser user = (AccountUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = user.getUserId();
        return Result.succ(userCenterService.getUserInfo(userId));
    }

    @PutMapping("/info")
    @ApiOperation("更新用户信息")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result updateUserInfo(@RequestBody @ApiParam("用户信息")UserProfile userProfile){
        AccountUser user = (AccountUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = user.getUserId();
        userCenterService.updateUserInfo(userId,userProfile);
        return Result.succ("更新成功");
    }






}
