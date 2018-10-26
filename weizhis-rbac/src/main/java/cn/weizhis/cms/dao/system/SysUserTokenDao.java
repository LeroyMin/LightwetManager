package cn.weizhis.cms.dao.system;

import cn.weizhis.cms.common.BaseDao;
import cn.weizhis.cms.entity.system.SysUserTokenEntity;

/**
 * @Auther: minliang
 * @Date: 2018/10/9 15:46
 * @Description:
 */
public interface SysUserTokenDao extends BaseDao<SysUserTokenEntity> {
    SysUserTokenEntity queryByToken(String accessToken);
}
