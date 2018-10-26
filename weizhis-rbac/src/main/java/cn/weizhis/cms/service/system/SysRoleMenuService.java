package cn.weizhis.cms.service.system;

import java.util.List;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 14:47
 * @Description:
 */
public interface SysRoleMenuService {

    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);

}
