package cn.weizhis.cms.service.impl.system;

import cn.weizhis.cms.dao.system.SysDeptDao;
import cn.weizhis.cms.entity.system.SysDeptEntity;
import cn.weizhis.cms.service.system.SysDeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:16
 * @Description:
 */
@Service("sysDeptService")
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    private SysDeptDao sysDeptDao;

    @Override
    public SysDeptEntity queryObject(Long deptId) {
        return sysDeptDao.queryObject(deptId);
    }

    @Override
    public List<SysDeptEntity> queryList(Map<String, Object> map) {
        return sysDeptDao.queryList(map);
    }

    @Override
    public void save(SysDeptEntity sysDeptEntity) {
        sysDeptDao.save(sysDeptEntity);
    }

    @Override
    public void update(SysDeptEntity sysDeptEntity) {
        sysDeptDao.update(sysDeptEntity);
    }

    @Override
    public void delete(Long deptId) {
        sysDeptDao.delete(deptId);
    }

    @Override
    public List<Long> queryOrgIdList(Long parentId) {
        return sysDeptDao.queryOrgIdList(parentId);
    }

    @Override
    public String getSubOrgIdList(Long deptId) {
        //部门及子部门ID列表
        List<Long> orgIdList = new ArrayList<>();

        //获取子部门ID
        List<Long> subIdList = queryOrgIdList(deptId);
        getOrgTreeList(subIdList, orgIdList);

        //添加本部门
        orgIdList.add(deptId);

        String orgFilter = StringUtils.join(orgIdList, ",");
        return orgFilter;
    }

    /**
     * 递归
     */
    public void getOrgTreeList(List<Long> subIdList, List<Long> orgIdList){
        for(Long deptId : subIdList){
            List<Long> list = queryOrgIdList(deptId);
            if(list.size() > 0){
                getOrgTreeList(list, orgIdList);
            }
            orgIdList.add(deptId);
        }
    }

}
