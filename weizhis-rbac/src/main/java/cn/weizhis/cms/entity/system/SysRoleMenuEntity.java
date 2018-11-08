package cn.weizhis.cms.entity.system;

import cn.weizhis.cms.common.BaseEntity;
import lombok.Data;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:40
 * @Description:
 */
@Data
public class SysRoleMenuEntity extends BaseEntity {

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
