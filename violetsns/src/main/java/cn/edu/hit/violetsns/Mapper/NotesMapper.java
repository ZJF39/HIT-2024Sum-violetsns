package cn.edu.hit.violetsns.Mapper;

import cn.edu.hit.violetsns.Entity.pojo.Notes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotesMapper {
    List<Notes> getNotes();

    Notes getNoteInfo(Integer id);
}
