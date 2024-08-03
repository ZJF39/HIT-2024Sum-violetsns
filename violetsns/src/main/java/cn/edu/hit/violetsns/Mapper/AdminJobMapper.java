package cn.edu.hit.violetsns.Mapper;

import cn.edu.hit.violetsns.Entity.vo.AdminJobDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminJobMapper {


    List<AdminJobDetail> getAdminJobs(String title, String nickname, String status, String sortBy, String order);

    void updateJobStatus(Integer jobId, String status);
}
