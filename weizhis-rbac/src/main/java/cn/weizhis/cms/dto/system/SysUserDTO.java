package cn.weizhis.cms.dto.system;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:48
 * @Description:
 */
@Data
public class SysUserDTO {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 邮箱
     */
    @Email(message="邮箱格式不正确")
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 角色ID列表
     */
    private List<Long> roleIdList;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;
}
