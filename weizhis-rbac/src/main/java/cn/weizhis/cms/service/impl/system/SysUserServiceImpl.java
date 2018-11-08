package cn.weizhis.cms.service.impl.system;

import cn.weizhis.cms.common.Constant;
import cn.weizhis.cms.dao.system.SysUserDao;
import cn.weizhis.cms.entity.system.SysUserEntity;
import cn.weizhis.cms.exception.BizzException;
import cn.weizhis.cms.service.system.SysRoleService;
import cn.weizhis.cms.service.system.SysUserRoleService;
import cn.weizhis.cms.service.system.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:17
 * @Description:
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public List<String> queryAllperms(Long userId) {
        return sysUserDao.queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sysUserDao.queryAllMenuId(userId);
    }

    @Override
    public SysUserEntity queryUserByUserName(String userName) {
        return sysUserDao.queryByUserName(userName);
    }

    @Override
    public SysUserEntity queryUserByUserId(Long userId) {
        return sysUserDao.queryObject(userId);
    }

    @Override
    public List<SysUserEntity> queryList(Map<String, Object> map) {
        return sysUserDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sysUserDao.queryTotal(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysUserEntity userEntity) {
        userEntity.setCreateTime(new Date());
        //sha256加密
        userEntity.setPassword(new Sha256Hash(userEntity.getPassword()).toHex());
        sysUserDao.save(userEntity);

        //检查角色是否越权
        checkRole(userEntity);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(userEntity.getUserId(), userEntity.getRoleIdList());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(SysUserEntity userEntity) {
        if(StringUtils.isBlank(userEntity.getPassword())){
            userEntity.setPassword(null);
        }else{
            userEntity.setPassword(new Sha256Hash(userEntity.getPassword()).toHex());
        }
        sysUserDao.update(userEntity);

        //检查角色是否越权
        checkRole(userEntity);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(userEntity.getUserId(), userEntity.getRoleIdList());

    }

    @Override
    @Transactional
    public void deleteBatch(Long[] userIds) {
        sysUserDao.deleteBatch(userIds);
    }

    @Override
    public int updatePassword(Long userId, String password, String newPassword) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        return sysUserDao.updatePassword(map);
    }

    @Override
    public int remove(SysUserEntity userEntity) {
        return sysUserDao.remove(userEntity);
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(SysUserEntity user){
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if(user.getCreateUserId() == Constant.SUPER_ADMIN){
            return ;
        }

        //查询用户创建的角色列表
        List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

        //判断是否越权
        if(!roleIdList.containsAll(user.getRoleIdList())){
            throw new BizzException("新增用户所选角色，不是本人创建");
        }
    }

}
