package cn.weizhis.cms.entity.system;

import lombok.Data;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:42
 * @Description:
 */
@Data
public class SysRoleDeptEntity {
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 部门ID
     */
    private Long deptId;

}
