package cn.edu.hit.violetsns.Mapper;

import cn.edu.hit.violetsns.Entity.pojo.PostPics;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostPicsMapper {
    Integer insert(PostPics pics);
    Integer deleteById(Integer id);
    Integer deleteByPostId(Integer postId);
}
