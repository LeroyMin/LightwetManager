package cn.weizhis.cms.entity.banner;

import cn.weizhis.cms.common.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @Auther: minliang
 * @Date: 2018/10/17 10:18
 * @Description:
 */
@Data
public class BannerEntity extends BaseEntity {
    private String name;
    private String forwardType;
    private String forwardUrl;
    private String imageUrl;
    private String status;
    private String sort;
    private Date deleteTime;
    private Date remark;
}
