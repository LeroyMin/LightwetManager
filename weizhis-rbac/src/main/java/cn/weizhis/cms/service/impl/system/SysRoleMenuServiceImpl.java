package cn.weizhis.cms.service.impl.system;

import cn.weizhis.cms.dao.system.SysRoleMenuDao;
import cn.weizhis.cms.service.system.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 15:41
 * @Description:
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    @Transactional
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        if(menuIdList.size() == 0){
            return ;
        }
        //先删除角色与菜单关系
        sysRoleMenuDao.delete(roleId);

        //保存角色与菜单关系
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("menuIdList", menuIdList);
        sysRoleMenuDao.save(map);

    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return sysRoleMenuDao.queryMenuIdList(roleId);
    }
}
