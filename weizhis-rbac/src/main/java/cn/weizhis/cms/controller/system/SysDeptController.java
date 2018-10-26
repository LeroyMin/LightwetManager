package cn.weizhis.cms.controller.system;

import cn.weizhis.cms.common.Constant;
import cn.weizhis.cms.common.InvokeResult;
import cn.weizhis.cms.entity.system.SysDeptEntity;
import cn.weizhis.cms.service.system.SysDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:05
 * @Description:
 */
@RestController
@RequestMapping("/sys/org")
public class SysDeptController extends AbstractController {
    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:org:list")
    public List<SysDeptEntity> list(){
        Map<String, Object> map = new HashMap<>();
        //如果不是超级管理员，则只能查询本部门及子部门数据
        if(getUserId() != Constant.SUPER_ADMIN){
            map.put("orgFilter", sysDeptService.getSubOrgIdList(getDeptId()));
        }
        List<SysDeptEntity> orgList = sysDeptService.queryList(map);

        return orgList;
    }

    /**
     * 选择组织机构(添加、修改菜单)
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:org:select")
    public InvokeResult select(){
        InvokeResult result = new InvokeResult();
        Map<String, Object> map = new HashMap<>();
        //如果不是超级管理员，则只能查询本部门及子部门数据
        if(getUserId() != Constant.SUPER_ADMIN){
            map.put("orgFilter", sysDeptService.getSubOrgIdList(getDeptId()));
        }
        List<SysDeptEntity> orgList = sysDeptService.queryList(map);

        //添加一级部门
        if(getUserId() == Constant.SUPER_ADMIN){
            SysDeptEntity root = new SysDeptEntity();
            root.setDeptId(0L);
            root.setName("一级部门");
            root.setParentId(-1L);
            root.setOpen(true);
            orgList.add(root);
        }
        result.setData(orgList);
        return result;
    }

    /**
     * 上级部门Id(管理员则为0)
     */
    @RequestMapping("/info")
    @RequiresPermissions("sys:org:list")
    public InvokeResult info(){
        InvokeResult result = new InvokeResult();
        long orgId = 0;
        if(getUserId() != Constant.SUPER_ADMIN){
            SysDeptEntity org = sysDeptService.queryObject(getDeptId());
            orgId = org.getParentId();
        }
        result.setData(orgId);
        return result;
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{orgId}")
    @RequiresPermissions("sys:org:info")
    public InvokeResult info(@PathVariable("orgId") Long orgId){
        InvokeResult result = new InvokeResult();
        SysDeptEntity org = sysDeptService.queryObject(orgId);
        result.setData(org);
        return result;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:org:save")
    public InvokeResult save(@RequestBody SysDeptEntity org){
        InvokeResult result = new InvokeResult();
        sysDeptService.save(org);
        return result.sucess();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:org:update")
    public InvokeResult update(@RequestBody SysDeptEntity org){
        InvokeResult result = new InvokeResult();
        sysDeptService.update(org);
        return result.sucess();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:org:delete")
    public InvokeResult delete(long orgId){
        InvokeResult result = new InvokeResult();
        //判断是否有子部门
        List<Long> orgList = sysDeptService.queryOrgIdList(orgId);
        if(orgList.size() > 0){
            return result.failure("请先删除子部门");
        }

        sysDeptService.delete(orgId);

        return result.sucess();
    }

}
