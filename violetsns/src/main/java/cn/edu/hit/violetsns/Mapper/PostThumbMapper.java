package cn.edu.hit.violetsns.Mapper;

import cn.edu.hit.violetsns.Entity.pojo.PostThumbs;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface PostThumbMapper {
    ArrayList<PostThumbs> findByPostId(Integer postId);
}
