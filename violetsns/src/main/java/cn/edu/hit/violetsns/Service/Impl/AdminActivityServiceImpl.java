package cn.edu.hit.violetsns.Service.Impl;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.AdminActivityCommentDetail;
import cn.edu.hit.violetsns.Entity.vo.AdminActivityDetail;
import cn.edu.hit.violetsns.Entity.vo.AdminPostDetail;
import cn.edu.hit.violetsns.Mapper.AdminActivityMapper;
import cn.edu.hit.violetsns.Service.AdminActivityService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminActivityServiceImpl implements AdminActivityService {
    @Autowired
    private AdminActivityMapper adminActivityMapper;

    @Override
    public PageBean getAdminAllActivity(Integer page, Integer pageSize, String nickname, String title, String content, String status, String sortBy, String order) {
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
        List<AdminActivityDetail> list = adminActivityMapper.getAdminAllActivity(nickname,title,content,status,sortBy,order);

        Page<AdminActivityDetail> P = (Page<AdminActivityDetail>) list;
        //3.封装
        PageBean pageBean = new PageBean(P.getTotal(), P.getResult());
        return pageBean;
    }

    @Override
    public PageBean getAdminActivityComment(Integer page, Integer pageSize, String nickname, String content, String status, String sortBy, String order) {
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
        List<AdminActivityCommentDetail> list = adminActivityMapper.getAdminActivityComment(nickname,content,status,sortBy,order);

        Page<AdminActivityCommentDetail> P = (Page<AdminActivityCommentDetail>) list;
        //3.封装
        PageBean pageBean = new PageBean(P.getTotal(), P.getResult());
        return pageBean;
    }

    @Override
    public void updateAdminActivityStatus(Integer activityId, String status) {
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
        adminActivityMapper.updateAdminActivityStatus(activityId,status);
    }
}
