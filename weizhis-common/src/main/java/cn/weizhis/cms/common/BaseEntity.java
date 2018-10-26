package cn.weizhis.cms.common;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: minliang
 * @Date: 2018/10/17 11:01
 * @Description:
 */
@Data
public abstract class BaseEntity {
    private String id;
    private String bizId;
    private String creator;
    private long creatorId;
    private String updator;
    private String updatorId;
    private Date createTime;
    private Date updateTime;
}
