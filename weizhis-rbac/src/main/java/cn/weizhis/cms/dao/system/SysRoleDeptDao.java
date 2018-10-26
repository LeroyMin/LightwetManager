package cn.weizhis.cms.dao.system;

import cn.weizhis.cms.common.BaseDao;
import cn.weizhis.cms.entity.system.SysRoleDeptEntity;
import java.util.List;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 14:29
 * @Description:
 */
public interface SysRoleDeptDao extends BaseDao<SysRoleDeptEntity> {

    List<Long> listDeptId(Long roleId);

    List<Long> listRoleId(Long orgId);

    int batchRemoveByDeptId(Long[] id);

    int batchRemoveByRoleId(Long[] id);

}
