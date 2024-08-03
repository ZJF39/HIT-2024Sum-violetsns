package cn.edu.hit.violetsns.Controller.User;

import cn.edu.hit.violetsns.Entity.vo.Result;
import com.aliyun.oss.AliOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadDemo {

    @Autowired
    private AliOSSUtils aliOSSUtils;

    @PostMapping("/test")
    public Result test(MultipartFile file) throws Exception {
        String url = aliOSSUtils.upload(file);
        // url -> 存储地址 -> 存入数据库进行后续操作
        return Result.succ(url);
    }

}
