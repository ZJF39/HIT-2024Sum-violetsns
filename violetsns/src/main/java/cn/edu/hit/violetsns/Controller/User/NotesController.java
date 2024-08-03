package cn.edu.hit.violetsns.Controller.User;

import cn.edu.hit.violetsns.Entity.pojo.Notes;
import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.Result;
import cn.edu.hit.violetsns.Service.NotesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "公告模块")
@RequestMapping("/api/notices")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @GetMapping("")
    @ApiOperation("获取公告")
    public Result getNotes(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "5") Integer pageSize){
        PageBean pageBean = notesService.getNotes(page,pageSize);
        return Result.succ(pageBean);
    }

    @GetMapping("/{id}")
    @ApiOperation("获取公告详情")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result getNoteInfo(@PathVariable Integer id){
        Notes note = notesService.getNoteInfo(id);
        return Result.succ(note);
    }
}
