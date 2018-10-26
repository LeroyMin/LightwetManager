package cn.weizhis.cms.service.system;

import cn.weizhis.cms.entity.system.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:12
 * @Description:
 */
public interface SysUserService {

    /**
     * 查询用户所有的权限
     * @param userId
     * @return
     */
    List<String> queryAllperms(Long userId);

    /**
     * 查询用户所有的菜单ID
     * @param userId
     * @return
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名查询系统用户信息
     * @param userName
     * @return
     */
    SysUserEntity queryUserByUserName(String userName);

    /**
     * 根据userId查询系统用户信息
     * @param userId
     * @return
     */
    SysUserEntity queryUserByUserId(Long userId);

    /**
     * 查询用户列表
     * @param map
     * @return
     */
    List<SysUserEntity> queryList(Map<String, Object> map);

    /**
     * 查询总数
     * @param map
     * @return
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存用户
     * @param userEntity
     * @return
     */
    void save(SysUserEntity userEntity);

    /**
     * 修改用户
     * @param userEntity
     * @return
     */
    void updateUser(SysUserEntity userEntity);

    /**
     * 删除用户
     * @param userIds
     * @return
     */
    void deleteBatch(Long[] userIds);

    /**
     * 修改密码
     * @param userId       用户ID
     * @param password     原密码
     * @param newPassword  新密码
     * @return
     */
    int updatePassword(Long userId, String password, String newPassword);

}
