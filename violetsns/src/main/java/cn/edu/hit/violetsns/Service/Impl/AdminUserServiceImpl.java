package cn.edu.hit.violetsns.Service.Impl;

import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.pojo.SysUser;
import cn.edu.hit.violetsns.Entity.pojo.UserDetails;
import cn.edu.hit.violetsns.Entity.vo.AdminUserDetail;
import cn.edu.hit.violetsns.Entity.vo.CountTMF;
import cn.edu.hit.violetsns.Entity.vo.DailyUserCount;
import cn.edu.hit.violetsns.Mapper.AdminUserMapper;
import cn.edu.hit.violetsns.Service.AdminUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;
    @Override
    public PageBean getUsersInfo(Integer page, Integer pageSize, String username, String nickname) {
        PageHelper.startPage(page, pageSize);
        //2.查询
        List<SysUser> sysUsersList = adminUserMapper.getUsersInfo(username, nickname);
        Page<SysUser> P = (Page<SysUser>) sysUsersList;
        //3.封装
        PageBean pageBean = new PageBean(P.getTotal(), P.getResult());
        return pageBean;
    }

    @Override
    public AdminUserDetail getUsersDetail(Integer userId) {
        SysUser sysUser = adminUserMapper.getUserById(userId);
        sysUser.setStatus(sysUser.getStatus().equals("0") ? "正常" : "禁用");
        UserDetails userDetails = adminUserMapper.getUserDetails(userId);
        String gender = userDetails.getGender();
        switch (gender) {
            case "0":
                userDetails.setGender("未知");
                break;
            case "1":
                userDetails.setGender("男");
                break;
            case "2":
                userDetails.setGender("女");
                break;
        }
        AdminUserDetail adminUserDetail = new AdminUserDetail(userId,sysUser.getUsername(), sysUser.getNickname(),sysUser.getAvatar(),sysUser.getStatus() ,userDetails.getCreateTime(), userDetails.getUpdateTime(), userDetails.getMobile(),userDetails.getGender(), userDetails.getSchool());
        return adminUserDetail;
    }

    @Override
    public void updateUserStatus(Integer userId, String status) {
        status = status.equals("正常") ? "0" : "1";
        adminUserMapper.updateUserStatus(userId, status);
    }

    @Override
    public CountTMF count() {
        CountTMF countTMF = adminUserMapper.count();
        return countTMF;
    }

    @Override
    public List<DailyUserCount> count30days() {
        List<DailyUserCount> list = adminUserMapper.count30days();
        return list;
    }
}
