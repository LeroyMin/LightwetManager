package cn.weizhis.cms.service.banner;

import cn.weizhis.cms.entity.banner.BannerEntity;
import java.util.List;
import java.util.Map;

/**
 * @Auther: minliang
 * @Date: 2018/10/19 16:40
 * @Description:
 */
public interface BannerService {
    void saveBanner(BannerEntity bannerEntity);
    List<BannerEntity> queryBannerList(Map param);
    int queryTotal();
    void repeal(BannerEntity bannerEntity);
}
