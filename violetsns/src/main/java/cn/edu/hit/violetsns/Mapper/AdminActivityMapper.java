package cn.edu.hit.violetsns.Mapper;

import cn.edu.hit.violetsns.Entity.vo.AdminActivityCommentDetail;
import cn.edu.hit.violetsns.Entity.vo.AdminActivityDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminActivityMapper {
    List<AdminActivityDetail> getAdminAllActivity(String nickname, String title, String content, String status, String sortBy, String order);

    List<AdminActivityCommentDetail> getAdminActivityComment(String nickname, String content, String status, String sortBy, String order);

    void updateAdminActivityStatus(Integer activityId, String status);
}
