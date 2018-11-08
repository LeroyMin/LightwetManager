package cn.weizhis.cms.entity.system;

import cn.weizhis.cms.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:23
 * @Description:
 */
@Data
public class SysUserEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -1716610944195230414L;
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

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
     * 创建时间
     */
    private Date createTime;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 创建者ID
     */
    private Long createUserId;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 用户头像
     */
    private String avator;

    // 逻辑和视图相关字段
    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 角色ID列表
     */
    private List<Long> roleIdList;


}
