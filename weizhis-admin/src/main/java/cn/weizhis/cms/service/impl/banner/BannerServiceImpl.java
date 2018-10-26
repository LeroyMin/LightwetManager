package cn.weizhis.cms.service.impl.banner;

import cn.weizhis.cms.dao.banner.BannerDao;
import cn.weizhis.cms.entity.banner.BannerEntity;
import cn.weizhis.cms.service.banner.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: minliang
 * @Date: 2018/10/19 16:41
 * @Description:
 */
@Service("bannerService")
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override
    public void saveBanner(BannerEntity bannerEntity) {
        bannerDao.save(bannerEntity);
    }

    @Override
    public List<BannerEntity> queryBannerList(Map param) {
        List<BannerEntity> list =  bannerDao.queryList(param);
        return list;
    }

    @Override
    public int queryTotal() {
        return bannerDao.queryTotal();
    }

    @Override
    public void repeal(BannerEntity bannerEntity) {
        bannerEntity.setDeleteTime(new Date());
        bannerDao.update(bannerEntity);
    }
}
