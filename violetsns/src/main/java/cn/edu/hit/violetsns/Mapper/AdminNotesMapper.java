package cn.edu.hit.violetsns.Mapper;

import cn.edu.hit.violetsns.Entity.pojo.Notes;
import cn.edu.hit.violetsns.Entity.vo.AdminNotesDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminNotesMapper {


    /**
     * 获取公告列表
     * @param
     * @return
     */
    List<AdminNotesDetail> getRows(String title,String content,String sortBy,String order);

    /**
     * 上传公告
     * @param notes
     */
    void uploadNotes(Notes notes);

    @Delete("delete from sns.notes where id = #{id}")
    void deleteNotes(Integer id);
}
