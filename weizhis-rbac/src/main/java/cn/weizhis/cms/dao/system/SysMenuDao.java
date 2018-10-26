package cn.weizhis.cms.dao.system;

import cn.weizhis.cms.common.BaseDao;
import cn.weizhis.cms.entity.system.SysMenuEntity;

import java.util.List;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:08
 * @Description:
 */
public interface SysMenuDao extends BaseDao<SysMenuEntity> {
    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenuEntity> queryNotButtonList();

    /**
     * 查询用户的权限列表
     */
    List<SysMenuEntity> queryUserList(Long userId);

}
