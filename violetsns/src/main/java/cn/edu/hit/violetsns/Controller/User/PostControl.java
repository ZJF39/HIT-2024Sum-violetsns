package cn.edu.hit.violetsns.Controller.User;

import cn.edu.hit.violetsns.Entity.pojo.AccountUser;
import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.pojo.Post;
import cn.edu.hit.violetsns.Entity.vo.PostComment;
import cn.edu.hit.violetsns.Entity.vo.PostDetail;
import cn.edu.hit.violetsns.Entity.vo.PostElement;
import cn.edu.hit.violetsns.Entity.vo.Result;
import cn.edu.hit.violetsns.Service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/posts")
@Api(tags = "博文模块")
public class PostControl {

    @Autowired
    private PostService postService;



    /**
     * 在数据库中添加一条post数据，需涉及post、post_comments
     * 、post_pics三张表
     * @param post 待添加的post对象
     * @return result状态信息
     */
    @PostMapping("")
    @ApiOperation("添加博客")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result insert(@RequestBody  Post post){
        AccountUser user = (AccountUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = user.getUserId();
        post.setUid(userId);
        if(post.getCoverPicture()==null){
            post.setCoverPicture("/default-image");
        }
        postService.insert(post);
        return Result.succ("发布博客成功");
    }

    /**
     * 展示post的详情页面，包括博文id、标题、正文、用户昵称、用户头像
     * @param id 博文id
     * @return 博文详情页面数据
     */
    // todo 测试接口
    @GetMapping("/{id}")
    @ApiOperation("展示详情页")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result findById(@PathVariable Integer id,
                           @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10")Integer pageSize){

         PostDetail  postDetail = postService.findById(id,page,pageSize);
        return Result.succ(postDetail);
    }

    @PutMapping("/{id}")
    @ApiOperation("点赞")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result thumbUp(@PathVariable("id") Integer postId){
        postService.thumbUp(postId);
        return Result.succ("点赞成功");
    }

    @GetMapping("")
    @ApiOperation("查询博文(关键字检索、用户id检索)")
    public Result findAll(@RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          String keyword,Integer userId){
        PageBean pageBean = postService.getPosts(page,pageSize,keyword,userId);
        return Result.succ(pageBean);
    }

    @PostMapping("/comment")
    @ApiOperation("评论")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result insertComment(@RequestBody PostComment postComment){
        AccountUser user = (AccountUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = user.getUserId();
        postComment.setUid(userId);
        postService.insertComment(postComment);
        return Result.succ("评论成功");
    }
}
