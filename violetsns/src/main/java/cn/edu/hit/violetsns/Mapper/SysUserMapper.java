package cn.edu.hit.violetsns.Mapper;


import cn.edu.hit.violetsns.Entity.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SysUserMapper {

    /**
     * 根据用户ID获取用户信息。
     *
     * @param username 用户名
     * @return 用户对象，包含相关用户信息。
     */
    SysUser getUserByName(String username);

    /**
     * 根据用户ID获取用户信息。
     *
     * @param userId 用户的唯一标识ID。
     * @return 用户对象，包含相关用户信息。
     */
    SysUser getUserById(Integer userId);

    /**
     * 获取指定用户ID的菜单列表。
     *
     * @param userId 用户的唯一标识ID。
     * @return 菜单名称的列表，用户拥有这些菜单的访问权限。
     */
    List<String> getMenuList(Integer userId);

    /**
     * 检查是否存在指定的用户名。
     *
     * @param username 待检查的用户名。
     * @return 如果存在该用户名返回true，否则返回false。
     */
    boolean hasUsername(String username);

    /**
     * 注册新用户。
     *
     * @param user 包含新用户信息的对象。
     */
    void register(SysUser user);

    /**
     * 为新注册的用户插入默认角色。
     *
     * @param userId 新注册用户的ID。
     */
    void insertDefaultRole(Integer userId);

    Integer selectUserID(String username);
}
