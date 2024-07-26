package cn.edu.hit.violetsns.Service;


import cn.edu.hit.violetsns.Entity.pojo.SysUser;

import java.util.List;

public interface SysUserService {

    public SysUser getUserByUsername(String username);

    List<String> getUserAuthorityInfo(Integer userId);
}
