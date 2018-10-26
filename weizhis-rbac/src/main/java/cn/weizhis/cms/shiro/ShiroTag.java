package cn.weizhis.cms.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

/**
 * @Auther: minliang
 * @Date: 2018/10/10 11:41
 * @Description:
 */
@Component
public class ShiroTag {
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
