package cn.weizhis.cms.service.system;

import cn.weizhis.cms.entity.system.SysDeptEntity;
import cn.weizhis.cms.entity.system.SysRoleDeptEntity;

import java.util.List;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 14:51
 * @Description:
 */
public interface SysRoleDeptService {
    void saveOrUpdate(SysRoleDeptEntity sysRoleDeptEntity);

    /**
     * 根据角色ID查询组织机构
     * @param roleId
     * @return
     */
    List<Long> listOrgId(Long roleId);

    /**
     * 根据组织机构查询角色
     * @param deptId
     * @return
     */
    List<Long> listRoleId(Long deptId);

    /**
     * 根据角色ID删除
     * @param roleId
     */
    void delete(Long roleId);

}
