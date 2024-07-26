package cn.edu.hit.violetsns.Entity.pojo;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountUser implements UserDetails {

    private SysUser user;

    /**
     * 用户ID，用于唯一标识一个用户。
     */
    private Integer userId;

    /**
     * 序列化版本ID，用于保证序列化和反序列化的兼容性。
     */
    private static final long serialVersionUID = 540L;

    /**
     * 日志对象，用于记录类的运行时日志。
     */
    private static final Log logger = LogFactory.getLog(User.class);

    /**
     * 用户密码，用于验证用户身份。
     */
    private String password;

    /**
     * 用户名，用于唯一标识一个用户，不可更改。
     */
    private  String username;

    /**
     * 用户权限集合，用于定义用户具有的操作权限。
     */
    @JsonIgnore
    private  Collection<? extends GrantedAuthority> authorities;

    private List<String> permissions;
    /**
     * 标志位，表示账户是否未过期，用于账户管理。
     */
    private  boolean accountNonExpired;

    /**
     * 标志位，表示账户是否未被锁定，用于账户管理。
     */
    private  boolean accountNonLocked;

    /**
     * 标志位，表示凭证（密码）是否未过期，用于账户管理。
     */
    private  boolean credentialsNonExpired;

    /**
     * 标志位，表示账户是否启用，用于账户管理。
     */
    private  boolean enabled;

    public AccountUser(Integer userId, String username, String password, List<String> permissions) {
        this(userId, username, password, true, true, true, true, permissions);
    }
    public AccountUser(SysUser user , Integer userId, String username, String password, List<String> permissions) {
        this(user,userId, username, password, true, true, true, true, permissions);
    }


    public AccountUser(Integer userId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, List<String> permissions) {
        Assert.isTrue(username != null && !"".equals(username) && password != null, "Cannot pass null or empty values to constructor");
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.permissions = permissions;
    }

    public AccountUser(SysUser user,Integer userId, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, List<String> permissions) {
        Assert.isTrue(username != null && !"".equals(username) && password != null, "Cannot pass null or empty values to constructor");
        this.user = user;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.permissions = permissions;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities != null )
            return this.authorities;
        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;

    }

    public AccountUser() {
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}

