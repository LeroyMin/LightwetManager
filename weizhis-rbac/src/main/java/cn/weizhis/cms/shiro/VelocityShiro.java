package cn.weizhis.cms.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 18:02
 * @Description:
 */
public class VelocityShiro {
    /**
     * 是否拥有该权限
     * @param permission  权限标识
     * @return   true：是     false：否
     */
    public boolean hasPermission(String permission) {
        Subject subject = SecurityUtils.getSubject();
        return subject != null && subject.isPermitted(permission);
    }

}
