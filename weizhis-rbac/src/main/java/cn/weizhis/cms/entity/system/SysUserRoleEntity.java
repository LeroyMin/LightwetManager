package cn.weizhis.cms.entity.system;

import lombok.Data;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:40
 * @Description:
 */
@Data
public class SysUserRoleEntity {

    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

}
