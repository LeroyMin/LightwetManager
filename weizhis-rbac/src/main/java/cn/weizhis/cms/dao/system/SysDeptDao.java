package cn.weizhis.cms.dao.system;

import cn.weizhis.cms.common.BaseDao;
import cn.weizhis.cms.entity.system.SysDeptEntity;

import java.util.List;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:08
 * @Description:
 */
public interface SysDeptDao extends BaseDao<SysDeptEntity> {
    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<Long> queryOrgIdList(Long parentId);

}
