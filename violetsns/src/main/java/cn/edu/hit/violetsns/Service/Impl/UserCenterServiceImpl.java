package cn.edu.hit.violetsns.Service.Impl;

import cn.edu.hit.violetsns.Entity.pojo.SysUser;
import cn.edu.hit.violetsns.Entity.pojo.UserDetails;
import cn.edu.hit.violetsns.Entity.vo.UserProfile;
import cn.edu.hit.violetsns.Mapper.UserCenterMapper;
import cn.edu.hit.violetsns.Service.UserCenterService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return userProfile;
    }


    @Override
    public void updateUserInfo(Integer userId, UserProfile userProfile) {
        SysUser sysUser = new SysUser(userProfile.getNickname(), userProfile.getAvatar());
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
        UserDetails userDetails = new UserDetails(userProfile.getGender(), userProfile.getMobile(), userProfile.getSchool(), now());

        userCenterMapper.updateSysUser(userId, sysUser);
        userCenterMapper.updateUserDetails(userId, userDetails);
    }
}
