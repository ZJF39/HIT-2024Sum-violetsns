package cn.edu.hit.violetsns.Mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDetailsMapper {

    /**
     * 根据用户ID添加默认个人信息为null
     *
     * @param userId 新用户ID
     */
    void insertDefault(Integer userId);
}
