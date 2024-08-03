package cn.edu.hit.violetsns.Mapper;

import cn.edu.hit.violetsns.Entity.pojo.Resources;
import cn.edu.hit.violetsns.Entity.vo.PostElement;
import cn.edu.hit.violetsns.Entity.vo.ResourceDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ResourceMapper {


    List<PostElement> getAllResources(String keyword);

    Resources getResourceInfo(Integer id);

    void ResourceBrowsePlus(Integer id);

    void ResourceUpvotePlus(Integer id);

    void postResource(Resources resources);

    String downloadResource(Integer id);
    ArrayList<ResourceDetail> getResource(String username, String title, String content, Integer status, String sort_by, String order);

    Integer delete(Integer id);

}
