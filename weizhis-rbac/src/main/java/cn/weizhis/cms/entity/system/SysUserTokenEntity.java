package cn.weizhis.cms.entity.system;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: minliang
 * @Date: 2018/10/9 15:43
 * @Description:
 */
@Data
public class SysUserTokenEntity {
    //用户ID
    private Long userId;
    //token
    private String token;
    //过期时间
    private Date expireTime;
    //更新时间
    private Date updateTime;

}
