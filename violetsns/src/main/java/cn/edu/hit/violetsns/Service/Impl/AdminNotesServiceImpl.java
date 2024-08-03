package cn.edu.hit.violetsns.Service.Impl;

import cn.edu.hit.violetsns.Entity.pojo.Notes;
import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.AdminNotesDetail;
import cn.edu.hit.violetsns.Entity.vo.AdminPostDetail;
import cn.edu.hit.violetsns.Mapper.AdminNotesMapper;
import cn.edu.hit.violetsns.Service.AdminNotesService;
import cn.edu.hit.violetsns.Utils.RedisCache;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminNotesServiceImpl implements AdminNotesService {
    AdminNotesDetail adminNotesDetail;

    @Autowired
    private AdminNotesMapper adminNotesMapper;

    @Autowired
    private RedisCache redisCache;


    /**
     * 查询公告
     * @param page
     * @param pageSize
     * @param username
     * @return
     */
    @Override
    public PageBean getNotes(Integer page, Integer pageSize, String title,String content,String sortBy,String order) {
        PageHelper.startPage(page, pageSize);

//        adminNotesDetail.setRows(adminNotesMapper.getRows(username));

        List<AdminNotesDetail> list = adminNotesMapper.getRows(title, content,sortBy,order);

        Page<AdminNotesDetail> P = (Page<AdminNotesDetail>) list;

        PageBean pageBean = new PageBean(P.getTotal(), P.getResult());

        return pageBean;
    }

    /**
     * 上传公告
     * @param notes
     */
    @Override
    public void uploadNotes(Notes notes) {
        adminNotesMapper.uploadNotes(notes);
    }

    /**
     * 删除公告
     * @param id
     */
    @Override
    public void deleteNotes(Integer id) {
        adminNotesMapper.deleteNotes(id);
    }
}
