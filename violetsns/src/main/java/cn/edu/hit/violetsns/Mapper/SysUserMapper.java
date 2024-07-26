package cn.edu.hit.violetsns.Mapper;


import cn.edu.hit.violetsns.Entity.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SysUserMapper {

    SysUser getUserByName(String username);

    ArrayList<String> getRoleList(Integer userId);

    SysUser getUserById(Integer userId);

    List<String> getMenuList(Integer userId);

    boolean hasUsername(String username);


    void register(SysUser user);

    void insertDefaultRole(String username);
}
