package cn.edu.hit.violetsns.Service;

import cn.edu.hit.violetsns.Entity.pojo.Notes;
import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.AdminNotesDetail;

public interface AdminNotesService {

    PageBean getNotes(Integer page, Integer pageSize, String title,String content,String sortBy,String order);

    void uploadNotes(Notes notes);

    void deleteNotes(Integer id);
}
