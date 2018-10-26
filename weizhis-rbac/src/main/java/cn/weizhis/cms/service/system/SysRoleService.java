package cn.weizhis.cms.service.system;

import cn.weizhis.cms.entity.system.SysRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:11
 * @Description:
 */
public interface SysRoleService {

    SysRoleEntity queryObject(Long roleId);

    List<SysRoleEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(SysRoleEntity role);

    void update(SysRoleEntity role);

    void deleteBatch(Long[] roleIds);

    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);

}
