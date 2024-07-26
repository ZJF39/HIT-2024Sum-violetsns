package cn.edu.hit.violetsns.Service;


import cn.edu.hit.violetsns.Entity.pojo.SysUser;
import cn.edu.hit.violetsns.Entity.vo.Result;

public interface LoginService{

    Result login(SysUser user);


    Result register(SysUser user);
}
