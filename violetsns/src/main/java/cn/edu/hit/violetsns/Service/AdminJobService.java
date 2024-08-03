package cn.edu.hit.violetsns.Service;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;

public interface AdminJobService {
    PageBean getAdminJobs(Integer page, Integer pageSize, String title, String nickname, String status, String sortBy, String order);

    void updateJobStatus(Integer jobId, String status);
}
