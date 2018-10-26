package cn.weizhis.cms.service.system;

import cn.weizhis.cms.entity.system.SysUserRoleEntity;

import java.util.List;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 14:43
 * @Description:
 */
public interface SysUserRoleService {
    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);

    void delete(Long userId);

    List<SysUserRoleEntity> queryListByUserId(Long userId);

}
