package cn.edu.hit.violetsns.Controller.User;

import cn.edu.hit.violetsns.Entity.pojo.AccountUser;
import cn.edu.hit.violetsns.Entity.pojo.Job;
import cn.edu.hit.violetsns.Entity.pojo.JobCV;
import cn.edu.hit.violetsns.Entity.pojo.PageBean;
import cn.edu.hit.violetsns.Entity.vo.JobResume;
import cn.edu.hit.violetsns.Entity.vo.Result;
import cn.edu.hit.violetsns.Service.JobService;
import com.aliyun.oss.AliOSSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/jobs")
@Api(tags = "用户内推模块")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @GetMapping("")
    @ApiOperation("获取所有（包括某人发布的）内推信息")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result getAllJobs(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             String keyword,Integer uid, String sortBy, String order){
        PageBean pageBean = jobService.getAllJobs(page, pageSize,uid, keyword, sortBy, order);
        return Result.succ(pageBean);
    }

    @GetMapping("/{id}")
    @ApiOperation("获取内推详情")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    @ApiImplicitParam(name = "id", value = "内推id", required = true, dataType = "Integer")
    public Result getJobInfo(@PathVariable("id") Integer jobId){
        return Result.succ(jobService.getJobInfo(jobId));
    }

    @PostMapping("")
    @ApiOperation("发布内推信息")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result uploadJob(@RequestBody Job job){
        AccountUser user = (AccountUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = user.getUserId();
        job.setUid(userId);

        jobService.uploadJob(job);
        return Result.succ("发布内推信息成功！");
    }


    @PostMapping("/{id}")
    @ApiOperation("发送个人简历")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result postResume(@PathVariable("id")Integer jobId,@RequestBody String file) throws Exception {
        AccountUser user = (AccountUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = user.getUserId();
        jobService.uploadResume(jobId,userId,file);
        return Result.succ("上传简历成功！");
    }

    @PostMapping("/upload")
    @ApiOperation("上传简历文件")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "MultipartFile")
    })
    public Result uploadCV(@RequestBody MultipartFile file) throws Exception {
        String url = aliOSSUtils.upload(file);
        return Result.succ(url);
    }

    @GetMapping("/resume/{id}")
    @ApiOperation("获取所有简历")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result getAllResume(@PathVariable("id") Integer jobId){
        JobResume jobResume= jobService.getAllResume(jobId);
        return Result.succ(jobResume);
    }

    @GetMapping("/download/{id}")
    @ApiOperation("下载简历")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public Result downloadResume(@PathVariable("id") Integer CVId){
        return Result.succ(jobService.downloadResume(CVId));
    }


    @PutMapping("/{id}")
    @ApiOperation("点赞内推")
    @PreAuthorize("hasAnyAuthority('sys:user')")
    public void jobThumbPlus(@PathVariable("id") Integer jobId){
        jobService.jobThumbPlus(jobId);
    }



}
