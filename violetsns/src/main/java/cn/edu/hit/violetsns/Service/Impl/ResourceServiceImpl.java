package cn.edu.hit.violetsns.Service.Impl;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.pojo.Resources;
import cn.edu.hit.violetsns.Entity.vo.PostElement;
import cn.edu.hit.violetsns.Mapper.ResourceMapper;
import cn.edu.hit.violetsns.Service.ResourceService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public PageBean getAllResources(Integer page, Integer pageSize,String keyword) {
        PageHelper.startPage(page, pageSize);
        List<PostElement> list = resourceMapper.getAllResources(keyword);

        Page<PostElement> P = (Page<PostElement>) list;

        PageBean pageBean = new PageBean(P.getTotal(), P.getResult());

        return pageBean;
    }

    @Override
    public Resources getResourceInfo(Integer id) {
        Resources resources = resourceMapper.getResourceInfo(id);
        resourceMapper.ResourceBrowsePlus(id);
        return resources;
    }

    @Override
    public void ResourceUpvotePlus(Integer id) {
        resourceMapper.ResourceUpvotePlus(id);
    }

    @Override
    public void postResource(Resources resources) {
        resourceMapper.postResource(resources);
    }

    @Override
    public String downloadResource(Integer id) {
        String url = resourceMapper.downloadResource(id);
        return url;
    }
}
