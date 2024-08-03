package cn.edu.hit.violetsns.Service.Impl;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.AdminCommentDetail;
import cn.edu.hit.violetsns.Entity.vo.AdminJobDetail;
import cn.edu.hit.violetsns.Mapper.AdminJobMapper;
import cn.edu.hit.violetsns.Service.AdminJobService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminJobServiceImpl implements AdminJobService {

    @Autowired
    private AdminJobMapper adminJobMapper;


    @Override
    public PageBean getAdminJobs(Integer page, Integer pageSize, String title, String nickname, String status, String sortBy, String order) {
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
        List<AdminJobDetail> jobDetailList = adminJobMapper.getAdminJobs( title, nickname, status, sortBy, order);

        Page<AdminJobDetail> P = (Page<AdminJobDetail>) jobDetailList;
        //3.封装
        PageBean pageBean = new PageBean(P.getTotal(), P.getResult());
        return pageBean;
    }

    @Override
    public void updateJobStatus(Integer jobId, String status) {
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
        adminJobMapper.updateJobStatus(jobId,status);
    }
}
