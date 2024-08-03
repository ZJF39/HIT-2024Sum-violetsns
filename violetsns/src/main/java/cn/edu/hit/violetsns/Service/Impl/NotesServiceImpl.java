package cn.edu.hit.violetsns.Service.Impl;

import cn.edu.hit.violetsns.Entity.pojo.Notes;
import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.AdminNotesDetail;
import cn.edu.hit.violetsns.Mapper.NotesMapper;
import cn.edu.hit.violetsns.Service.NotesService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesServiceImpl implements NotesService {

    @Autowired
    private NotesMapper notesMapper;

    @Override
    public PageBean getNotes(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Notes> list = notesMapper.getNotes();

        Page<Notes> P = (Page<Notes>) list;

        PageBean pageBean = new PageBean(P.getTotal(), P.getResult());

        return pageBean;
    }

    @Override
    public Notes getNoteInfo(Integer id) {
        Notes note = notesMapper.getNoteInfo(id);
        return note;
    }
}
