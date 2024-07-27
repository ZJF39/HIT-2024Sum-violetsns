package cn.edu.hit.violetsns.Controller;


import cn.edu.hit.violetsns.Entity.pojo.AccountUser;
import cn.edu.hit.violetsns.Entity.pojo.SysUser;
import cn.edu.hit.violetsns.Entity.vo.Result;
import cn.edu.hit.violetsns.Service.LoginService;
import cn.edu.hit.violetsns.Utils.RedisCache;
import cn.edu.hit.violetsns.Utils.RedisUtils;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/auth")
@Api(tags = "登录模块")
public class LoginController {


    @Autowired
    private Producer producer;
    @Autowired
    private LoginService loginservice;
    @Autowired
    private RedisCache redisCache;



    @ApiOperation("测试接口")
    @PreAuthorize("hasAnyAuthority('sys:admin')")
    @GetMapping("/hello")
    public Result hello(){
        return Result.succ("hello");
    }



    @ApiOperation("注册接口")
    @PostMapping("/register")
    public Result register(@RequestBody SysUser user){
        loginservice.register(user);
        return Result.succ("注册成功!");
    }


    @ApiOperation("登录接口")
    @PostMapping("/login")
    public Result login(@RequestBody SysUser user){
        return loginservice.login(user);
    }



    @ApiOperation("退出接口")
    @PostMapping("/logout")
    public Result logout1(){
        AccountUser user = (AccountUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        redisCache.deleteObject("login:"+user.getUser().getUserId());
        return Result.succ("退出成功");
    }


    @ApiOperation("验证码接口")
    @GetMapping("/captcha")
    public Result Captcha() throws IOException {
        String key = UUID.randomUUID().toString();
        String code = producer.createText();

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);

        Base64.Encoder encoder = Base64.getEncoder();
        String str = "data:image/jpeg;base64,";

        String base64Img = str + encoder.encodeToString(outputStream.toByteArray());
        key ="captcha:"+ key;

        RedisUtils.StringOps.set(key, code);
            RedisUtils.KeyOps.expire(key, 3600, TimeUnit.SECONDS);

        return Result.succ(
                MapUtil.builder()
                        .put("userKey", key)
                        .put("captcherImg", base64Img)
                        .build()
        );
    }
}
