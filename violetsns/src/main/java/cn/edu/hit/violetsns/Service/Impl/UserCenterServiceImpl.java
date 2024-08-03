package cn.edu.hit.violetsns.Service.Impl;

import cn.edu.hit.violetsns.Entity.pojo.*;
import cn.edu.hit.violetsns.Entity.vo.PostDetail;
import cn.edu.hit.violetsns.Entity.vo.PostElement;
import cn.edu.hit.violetsns.Entity.vo.UserProfile;
import cn.edu.hit.violetsns.Entity.vo.VOPostComments;
import cn.edu.hit.violetsns.Mapper.UserCenterMapper;
import cn.edu.hit.violetsns.Service.UserCenterService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.LocalTime.now;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserCenterServiceImpl implements UserCenterService {

    @Autowired
    private UserCenterMapper userCenterMapper;

    @Override
    @Transactional(readOnly = true)
    public UserProfile getUserInfo(Integer userId) {
        UserProfile userProfile = userCenterMapper.getUserInfo(userId);
        String genderCode = userProfile.getGender(); // 假设gender字段存储的是数字编码
        switch (genderCode) {
            case "0":
                userProfile.setGender("未知");
                break;
            case "1":
                userProfile.setGender("男");
                break;
            case "2":
                userProfile.setGender("女");
                break;
        }
        userProfile.setUserId(userId);
        userProfile.setNUpvote(userCenterMapper.getTotalThumbsByUserId(userId));
        userProfile.setNPost(userCenterMapper.getTotalPostsByUserId(userId));
        userProfile.setNComment(userCenterMapper.getTotalCommentsByUserId(userId));
        return userProfile;
    }


    @Override
    public void updateUserInfo(Integer userId, UserProfile userProfile) {
        if(userProfile.getGender() != null){
            String genderCode = userProfile.getGender(); // 假设gender字段存储的是数字编码
            switch (genderCode) {
                case "未知":
                    userProfile.setGender("0");
                    break;
                case "男":
                    userProfile.setGender("1");
                    break;
                case "女":
                    userProfile.setGender("2");
                    break;
            }
        }
        userCenterMapper.updateSysUser(userId, userProfile);

        userCenterMapper.updateUserDetails(userId, userProfile);
    }

    @Override
    public PageBean getMyPosts(Integer userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<PostElement> list = userCenterMapper.getMyPosts(userId);

        Page<PostElement> P = (Page<PostElement>) list;

        PageBean pageBean = new PageBean(P.getTotal(), P.getResult());

        return pageBean;
    }

    @Override
    public PageBean getMyJobs(Integer userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<PostElement> list = userCenterMapper.getMyJobs(userId);
        Page<PostElement> P = (Page<PostElement>) list;
        PageBean pageBean = new PageBean(P.getTotal(), P.getResult());
        return pageBean;
    }

    @Override
    public PageBean getMyResumes(Integer userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<JobCV> list = userCenterMapper.getMyResumes(userId);
        Page<JobCV> P = (Page<JobCV>) list;
        PageBean pageBean = new PageBean(P.getTotal(), P.getResult());
        return pageBean;
    }

    @Override
    public PageBean getMyResources(Integer userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<PostElement> list = userCenterMapper.getMyResources(userId);
        Page<PostElement> P = (Page<PostElement>) list;
        PageBean pageBean = new PageBean(P.getTotal(), P.getResult());
        return pageBean;
    }
}
