package cn.edu.hit.violetsns.Entity.vo;

import cn.edu.hit.violetsns.Entity.pojo.JobCV;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobResume {
    private String jobTitle;
    private List<JobCV> resume;
}
