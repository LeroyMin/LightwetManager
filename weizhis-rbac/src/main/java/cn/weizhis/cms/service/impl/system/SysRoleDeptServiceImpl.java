package cn.weizhis.cms.service.impl.system;

import cn.weizhis.cms.dao.system.SysRoleDeptDao;
import cn.weizhis.cms.entity.system.SysRoleDeptEntity;
import cn.weizhis.cms.service.system.SysRoleDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 15:41
 * @Description:
 */
@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl implements SysRoleDeptService {
    @Autowired
    private SysRoleDeptDao sysRoleDeptDao;

    @Override
    public void saveOrUpdate(SysRoleDeptEntity sysRoleDeptEntity) {
        //先删除角色与机构关系
        sysRoleDeptDao.delete(sysRoleDeptEntity.getRoleId());

        //保存角色与机构关系
        sysRoleDeptDao.save(sysRoleDeptEntity);

    }

    @Override
    public List<Long> listOrgId(Long roleId) {
        return sysRoleDeptDao.listDeptId(roleId);
    }

    @Override
    public List<Long> listRoleId(Long deptId) {
        return sysRoleDeptDao.listRoleId(deptId);
    }

    @Override
    public void delete(Long roleId) {

    }
}
