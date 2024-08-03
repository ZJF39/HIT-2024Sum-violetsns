package cn.edu.hit.violetsns.Service;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;

public interface AdminResourceService {

    void delete(Integer id);
    PageBean getResourceDetail(Integer page, Integer pageSize, String username, String title, String content, Integer status, String sort_by, String order);
}
