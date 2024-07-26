package cn.edu.hit.violetsns.Service.Impl;


import cn.edu.hit.violetsns.Entity.pojo.AccountUser;
import cn.edu.hit.violetsns.Entity.pojo.SysUser;
import cn.edu.hit.violetsns.Entity.vo.Result;
import cn.edu.hit.violetsns.Mapper.SysUserMapper;
import cn.edu.hit.violetsns.Service.LoginService;
import cn.edu.hit.violetsns.Utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {


    @Resource
    private AuthenticationManager authenticationManager;
    @Autowired
    private SysUserMapper mapper;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private ShortIDUtils shortID;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Result login(SysUser sysUser) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                        sysUser.getUsername(), sysUser.getPassword()
                );
        Authentication authenticate =
                authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if(Objects.isNull(authenticate)){
            return Result.fail(402,"用户登录错误",null);
        }
        AccountUser user = (AccountUser) authenticate.getPrincipal();
        Integer userId = user.getUser().getUserId();
        String jwt = jwtUtils.generateToken(String.valueOf(userId));
        redisCache.setCacheObject("login:" + userId,user);

        Map<String,Object> map = new HashMap<>();
        map.put("token",jwt);

        SysUser sysUser1 = new SysUser();
        sysUser1.setUserId(user.getUser().getUserId());
        sysUser1.setUsername(user.getUser().getUsername());
        sysUser1.setAvatar(user.getUser().getAvatar());
        sysUser1.setNickname(user.getUser().getNickname());
        sysUser1.setStatus(user.getUser().getStatus());
        map.put("userInfo",sysUser1);

        map.put("menuList",mapper.getMenuList(userId));


        return Result.succ(200,"登陆成功",map);

    }



    @Override
    public Result register(SysUser user) {
        if(mapper.hasUsername(user.getUsername())){
            return Result.fail("用户名已存在");
        }
        //设置默认用户名
        if(StringUtils.isEmpty(user.getNickname())){
            user.setNickname(shortID.generateShortUuid());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        mapper.register(user);
        mapper.insertDefaultRole(user.getUsername());
        return Result.succ("注册成功");
    }
}
