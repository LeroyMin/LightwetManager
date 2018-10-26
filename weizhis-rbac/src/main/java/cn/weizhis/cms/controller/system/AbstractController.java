package cn.weizhis.cms.controller.system;

import cn.weizhis.cms.entity.system.SysUserEntity;
import org.apache.shiro.SecurityUtils;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 17:12
 * @Description:
 */
public abstract class AbstractController {

    protected SysUserEntity getUser() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }

    protected Long getDeptId() {
        return getUser().getDeptId();
    }
}
