package cn.edu.hit.violetsns.Service;

import cn.edu.hit.violetsns.Entity.pojo.Job;
import cn.edu.hit.violetsns.Entity.pojo.JobCV;
import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.JobDetail;
import cn.edu.hit.violetsns.Entity.vo.JobResume;

public interface JobService {
    PageBean getAllJobs(Integer page, Integer pageSize,Integer uid, String keyword, String sortBy, String order);

    JobDetail getJobInfo(Integer jobId);

    void jobThumbPlus(Integer jobId);

    void uploadJob(Job job);

    void uploadResume(Integer jobId, Integer userId, String url);

    JobResume getAllResume(Integer jobId);

    String downloadResume(Integer cvId);
}
