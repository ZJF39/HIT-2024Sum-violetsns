package cn.edu.hit.violetsns.Service.Impl;

import cn.edu.hit.violetsns.Entity.pojo.*;
import cn.edu.hit.violetsns.Entity.vo.*;
import cn.edu.hit.violetsns.Mapper.*;
import cn.edu.hit.violetsns.Service.PostService;
import cn.edu.hit.violetsns.Service.SysUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private PostCommentMapper postCommentMapper;
    @Autowired
    private PostThumbMapper postThumbMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private UserCenterMapper userCenterMapper;
    @Override
    public void insert(Post post) {
        post.setCreateTime(new Date());
        postMapper.insert(post);
    }


    @Override
    public PageBean getPosts(Integer page, Integer pageSize,String keyword,Integer uid) {
        PageHelper.startPage(page, pageSize);

        //2.查询
        List<PostElement> postsList = postMapper.getPosts(keyword,uid);

        Page<PostElement> P = (Page<PostElement>) postsList;
        //3.封装
        PageBean pageBean = new PageBean(P.getTotal(), P.getResult());
        return pageBean;

    }

    @Override
    // 根据博文id返回博文详情内容，并在PageBean中存储分页查询的评论内容
    public PostDetail findById(Integer postId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<VOPostComments> commentsList = postCommentMapper.findByPostId(postId);
        PostDetail postDetail = postMapper.findByPostId(postId);

        if (postDetail != null) {
            postMapper.postViewPlus(postId);
            Page<VOPostComments> P = (Page<VOPostComments>) commentsList;
            //3.封装
            PageBean pageBean = new PageBean(P.getTotal(), P.getResult());
            postDetail.setComments(pageBean);
        }

        return postDetail;
    }

    @Override
    public void thumbUp(Integer postId) {
        postMapper.thumbUp(postId);
    }

    @Override
    public void insertComment(PostComment postComment) {
        postMapper.insertComment(postComment);
    }

}

