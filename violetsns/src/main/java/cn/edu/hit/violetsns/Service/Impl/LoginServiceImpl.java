package cn.edu.hit.violetsns.Service.Impl;


import cn.edu.hit.violetsns.Entity.pojo.AccountUser;
import cn.edu.hit.violetsns.Entity.pojo.SysUser;
import cn.edu.hit.violetsns.Entity.vo.Result;
import cn.edu.hit.violetsns.Exception.LoginException;
import cn.edu.hit.violetsns.Handler.LoginSuccessHandler;
import cn.edu.hit.violetsns.Mapper.SysUserMapper;
import cn.edu.hit.violetsns.Mapper.UserDetailsMapper;
import cn.edu.hit.violetsns.Service.LoginService;
import cn.edu.hit.violetsns.Utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {


    @Resource
    private AuthenticationManager authenticationManager;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private UserDetailsMapper userDetailsMapper;
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

        map.put("menuList",sysUserMapper.getMenuList(userId));


        return Result.succ(200,"登陆成功",map);

    }



    @Override
    public Result register(SysUser user) {
        if(sysUserMapper.hasUsername(user.getUsername())){

            return Result.fail("用户名已存在");
        }
        //设置默认用户名
        if(StringUtils.isEmpty(user.getNickname())){
            user.setNickname(shortID.generateShortUuid());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        sysUserMapper.register(user);
        user.setUserId(sysUserMapper.selectUserID(user.getUsername()));
        sysUserMapper.insertDefaultRole(user.getUserId());
        userDetailsMapper.insertDefault(user.getUserId());

        return Result.succ("注册成功");
    }
}
