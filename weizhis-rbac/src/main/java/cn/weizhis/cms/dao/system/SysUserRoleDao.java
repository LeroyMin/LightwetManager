package cn.weizhis.cms.dao.system;

import cn.weizhis.cms.common.BaseDao;
import cn.weizhis.cms.entity.system.SysUserRoleEntity;

import java.util.List;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 14:27
 * @Description:
 */
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {
    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);

    List<SysUserRoleEntity> queryListByUserId(Long userId);

}
