package cn.weizhis.cms.dao.system;

import cn.weizhis.cms.common.BaseDao;
import cn.weizhis.cms.entity.system.SysRoleMenuEntity;

import java.util.List;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 14:26
 * @Description:
 */
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);

}
