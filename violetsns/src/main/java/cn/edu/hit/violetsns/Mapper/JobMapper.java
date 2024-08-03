package cn.edu.hit.violetsns.Mapper;

import cn.edu.hit.violetsns.Entity.pojo.Job;
import cn.edu.hit.violetsns.Entity.pojo.JobCV;
import cn.edu.hit.violetsns.Entity.vo.JobDetail;
import cn.edu.hit.violetsns.Entity.vo.JobResume;
import cn.edu.hit.violetsns.Entity.vo.PostElement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JobMapper {

    List<PostElement> getAllJobs(Integer uid,String keyword, String sortBy, String order);
    JobDetail getJobInfo(Integer jobId);

    void jobThumbPlus(Integer jobId);
    void jobBrowsePlus(Integer jobId);

    void uploadJob(Job job);

    void uploadResume(Integer jobId, Integer userId, String url);

    List<JobCV> getAllResume(Integer jobId);

    String getJobTitle(Integer jobId);

    String downloadResume(Integer cvId);
}
