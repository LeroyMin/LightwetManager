package cn.weizhis.cms.service.system;

import cn.weizhis.cms.common.InvokeResult;
import cn.weizhis.cms.entity.system.SysUserTokenEntity;

/**
 * @Auther: minliang
 * @Date: 2018/10/9 15:39
 * @Description:
 */
public interface SysUserTokenService {
    /**
     * 生成token
     * @param userId  用户ID
     */
    InvokeResult createToken(long userId);

    /**
     * 查询token
     * @param userId  用户ID
     */
    InvokeResult queryToken(long userId);

    /**
     * 查询token
     * @param accessToken  用户ID
     */
    SysUserTokenEntity queryByToken(String accessToken);

    /**
     * 退出，修改token值
     * @param userId  用户ID
     */
    void logout(long userId);

}
