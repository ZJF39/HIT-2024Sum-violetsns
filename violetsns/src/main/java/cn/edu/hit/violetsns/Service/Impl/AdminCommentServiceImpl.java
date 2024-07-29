package cn.edu.hit.violetsns.Service.Impl;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.AdminCommentDetail;
import cn.edu.hit.violetsns.Entity.vo.AdminPostDetail;
import cn.edu.hit.violetsns.Mapper.AdminCommentMapper;
import cn.edu.hit.violetsns.Service.AdminCommentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCommentServiceImpl implements AdminCommentService {

    @Autowired
    private AdminCommentMapper adminCommentMapper;

    @Override
    public PageBean getAdminAllComments(Integer page, Integer pageSize,Integer postId,Integer uid, String nickname, String content, String status, String sortBy, String order) {
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
        List<AdminCommentDetail> commentDetailsList = adminCommentMapper.getAdminAllComments( postId,uid,nickname, content,status, sortBy, order);

        Page<AdminCommentDetail> P = (Page<AdminCommentDetail>) commentDetailsList;
        //3.封装
        PageBean pageBean = new PageBean(P.getTotal(), P.getResult());
        return pageBean;
    }

    @Override
    public void updateCommentStatus(Integer commentId, String status) {
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
        adminCommentMapper.updateCommentStatus(commentId, status);
    }
}
