package cn.weizhis.cms.controller.banner;

import cn.weizhis.cms.common.InvokeResult;
import cn.weizhis.cms.common.Page;
import cn.weizhis.cms.entity.banner.BannerEntity;
import cn.weizhis.cms.service.banner.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/banner")
@Slf4j
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @PostMapping("/save")
    @ResponseBody
    public InvokeResult saveBanner(@RequestBody BannerEntity bannerEntity){
        InvokeResult result = new InvokeResult();
        if (bannerEntity == null) {
            return result.failure("入参不能为空");
        }
        try {
            bannerService.saveBanner(bannerEntity);
        }catch (Exception e){
            log.error("系统异常,{}",e);
            result.failure("系统异常");
        }
        return result;
    }

    @PostMapping("/queryBanners")
    @ResponseBody
    public InvokeResult<Page<BannerEntity>> queryBanners(@RequestBody Page page){
        InvokeResult result = new InvokeResult();
        Map<String,Object> map = new HashMap<>();
        map.put("currPage", (page.getCurrPage() -1) * page.getPageSize());
        map.put("pageSize", page.getPageSize());
        try {
            int total = bannerService.queryTotal();
            if (total <= 0){
                return result.failure("暂无结果,请新增");
            }
            List<BannerEntity> list = bannerService.queryBannerList(map);
            Page resPage = new Page(list,total,page.getPageSize(),page.getCurrPage());
            result.setData(resPage);
        }catch (Exception e){
            log.error("系统异常:{}",e);
            result.failure("系统异常");
        }
        return result;
    }

    @PostMapping("/repeal")
    @ResponseBody
    public InvokeResult repeal(@RequestBody BannerEntity bannerEntity){
        InvokeResult result = new InvokeResult();
        try {
            bannerService.repeal(bannerEntity);
        }catch (Exception e){
            log.error("系统异常:{}",e);
            result.failure("系统异常");
        }
        return result;
    }
}
