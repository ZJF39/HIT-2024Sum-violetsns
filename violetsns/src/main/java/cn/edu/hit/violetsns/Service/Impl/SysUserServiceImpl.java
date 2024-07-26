package cn.edu.hit.violetsns.Service.Impl;


import cn.edu.hit.violetsns.Entity.pojo.SysUser;
import cn.edu.hit.violetsns.Mapper.SysUserMapper;
import cn.edu.hit.violetsns.Service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser getUserByUsername(String username) {
        return sysUserMapper.getUserByName(username);
    }

    @Override
    public List<String> getUserAuthorityInfo(Integer userId) {
        SysUser sysUser = sysUserMapper.getUserById(userId);
        //查询用户角色列表
        List<String> permissions = sysUserMapper.getMenuList(sysUser.getUserId());
        return permissions;
    }
}
