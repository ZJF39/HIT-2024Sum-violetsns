package cn.edu.hit.violetsns.Service;

import cn.edu.hit.violetsns.Entity.pojo.Notes;
import cn.edu.hit.violetsns.Entity.pojo.PageBean;

public interface NotesService {
    PageBean getNotes(Integer page, Integer pageSize);

    Notes getNoteInfo(Integer id);
}
