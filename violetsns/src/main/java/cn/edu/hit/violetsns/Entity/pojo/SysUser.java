package cn.edu.hit.violetsns.Entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {
    private Integer userId;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String status;
}
