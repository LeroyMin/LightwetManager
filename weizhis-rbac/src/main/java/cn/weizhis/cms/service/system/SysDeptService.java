package cn.weizhis.cms.service.system;

import cn.weizhis.cms.entity.system.SysDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:11
 * @Description:
 */
public interface SysDeptService {
    SysDeptEntity queryObject(Long deptId);

    List<SysDeptEntity> queryList(Map<String, Object> map);

    void save(SysDeptEntity sysDeptEntity);

    void update(SysDeptEntity sysDeptEntity);

    void delete(Long deptId);

    /**
     * 查询子部门ID列表
     * @param parentId  上级部门ID
     */
    List<Long> queryOrgIdList(Long parentId);

    /**
     * 获取子部门ID(包含本部门ID)，用于数据过滤
     */
    String getSubOrgIdList(Long deptId);

}
