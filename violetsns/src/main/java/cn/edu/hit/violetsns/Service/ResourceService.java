package cn.edu.hit.violetsns.Service;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.pojo.Resources;

public interface ResourceService {

    PageBean getAllResources(Integer page, Integer pageSize,String keyword);

    Resources getResourceInfo(Integer id);

    void ResourceUpvotePlus(Integer id);

    void postResource(Resources resources);

    String downloadResource(Integer id);
}
