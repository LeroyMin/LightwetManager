package cn.weizhis.cms.dao.system;

import cn.weizhis.cms.common.BaseDao;
import cn.weizhis.cms.entity.system.SysRoleEntity;

import java.util.List;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:08
 * @Description:
 */
public interface SysRoleDao extends BaseDao<SysRoleEntity> {
    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);

}
