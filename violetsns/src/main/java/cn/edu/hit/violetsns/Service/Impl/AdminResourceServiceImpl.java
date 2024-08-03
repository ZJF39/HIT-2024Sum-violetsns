package cn.edu.hit.violetsns.Service.Impl;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.ResourceDetail;
import cn.edu.hit.violetsns.Mapper.ResourceMapper;
import cn.edu.hit.violetsns.Service.AdminResourceService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminResourceServiceImpl implements AdminResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public void delete(Integer id) {
        resourceMapper.delete(id);
    }

    @Override
    public PageBean getResourceDetail(Integer page, Integer pageSize, String username, String title, String content, Integer status, String sort_by, String order) {
        PageHelper.startPage(page, pageSize);
        ArrayList<ResourceDetail> result = resourceMapper.getResource(username, title, content, status, sort_by, order);
        Page<ResourceDetail> P = (Page<ResourceDetail>) result;
        PageBean pageBean = new PageBean(P.getTotal(), P.getResult());
        return pageBean;
    }
}
