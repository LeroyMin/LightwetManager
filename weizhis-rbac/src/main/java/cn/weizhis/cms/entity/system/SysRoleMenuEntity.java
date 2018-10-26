package cn.weizhis.cms.entity.system;

import lombok.Data;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:40
 * @Description:
 */
@Data
public class SysRoleMenuEntity {

    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

}
