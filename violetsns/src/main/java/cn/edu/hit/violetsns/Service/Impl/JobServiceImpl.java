package cn.edu.hit.violetsns.Service.Impl;

import cn.edu.hit.violetsns.Entity.pojo.Job;
import cn.edu.hit.violetsns.Entity.pojo.JobCV;
import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.AdminJobDetail;
import cn.edu.hit.violetsns.Entity.vo.JobDetail;
import cn.edu.hit.violetsns.Entity.vo.JobResume;
import cn.edu.hit.violetsns.Entity.vo.PostElement;
import cn.edu.hit.violetsns.Mapper.JobMapper;
import cn.edu.hit.violetsns.Service.JobService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobMapper jobMapper;


    @Override
    public PageBean getAllJobs(Integer page, Integer pageSize, Integer uid,String keyword, String sortBy, String order) {
        PageHelper.startPage(page, pageSize);
        //2.查询
        List<PostElement> list = jobMapper.getAllJobs(uid,keyword, sortBy, order);

        Page<PostElement> P = (Page<PostElement>) list;
        //3.封装
        PageBean pageBean = new PageBean(P.getTotal(), P.getResult());
        return pageBean;
    }

    @Override
    public JobDetail getJobInfo(Integer jobId) {
        JobDetail jobDetail = jobMapper.getJobInfo(jobId);
        if(jobDetail != null){
            jobMapper.jobBrowsePlus(jobId);
        }
        return jobDetail;
    }

    @Override
    public void jobThumbPlus(Integer jobId) {
        jobMapper.jobThumbPlus(jobId);
    }

    @Override
    public void uploadJob(Job job) {

        jobMapper.uploadJob(job);
    }

    @Override
    public void uploadResume(Integer jobId, Integer userId, String url) {
        jobMapper.uploadResume(jobId,userId,url);
    }

    @Override
    public JobResume getAllResume(Integer jobId) {
        JobResume  jobResume = new JobResume();
        jobResume.setResume(jobMapper.getAllResume(jobId));
        jobResume.setJobTitle(jobMapper.getJobTitle(jobId));
        return jobResume;
    }

    @Override
    public String downloadResume(Integer cvId) {
        return jobMapper.downloadResume(cvId);

    }
}
