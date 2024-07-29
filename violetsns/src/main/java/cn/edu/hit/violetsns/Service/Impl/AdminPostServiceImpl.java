package cn.edu.hit.violetsns.Service.Impl;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.pojo.SysUser;
import cn.edu.hit.violetsns.Entity.pojo.UserDetails;
import cn.edu.hit.violetsns.Entity.vo.AdminPostDetail;
import cn.edu.hit.violetsns.Mapper.AdminPostMapper;
import cn.edu.hit.violetsns.Service.AdminPostService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPostServiceImpl implements AdminPostService {

    @Autowired
    private AdminPostMapper adminUserMapper;

    @Override
    public PageBean getAdminAllPosts(Integer page, Integer pageSize, Integer postId, String username, String nickname, String title, String content,String status, String sortBy, String order) {
        PageHelper.startPage(page, pageSize);
        if(status!=null){
            switch (status) {
                case "正常":
                    status="0";
                    break;
                case "删除":
                    status="2";
                    break;
                default:
                    status="1";
                    break;
            }
        }
        //2.查询
        List<AdminPostDetail> postDetailsList = adminUserMapper.getAdminAllPosts(postId, username, nickname, title, content,status, sortBy, order);

        Page<AdminPostDetail> P = (Page<AdminPostDetail>) postDetailsList;
        //3.封装
        PageBean pageBean = new PageBean(P.getTotal(), P.getResult());
        return pageBean;
    }

    @Override
    public void updatePostStatus(Integer postId, String status) {
        switch (status) {
            case "正常":
                status="0";
                break;
            case "删除":
                status="2";
                break;
            default:
                status="1";
                break;
        }
        adminUserMapper.updatePostStatus(postId, status);
    }
}
