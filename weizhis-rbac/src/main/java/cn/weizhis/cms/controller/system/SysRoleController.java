package cn.weizhis.cms.controller.system;

import cn.weizhis.cms.common.Constant;
import cn.weizhis.cms.common.InvokeResult;
import cn.weizhis.cms.common.Page;
import cn.weizhis.cms.common.Query;
import cn.weizhis.cms.entity.system.SysRoleEntity;
import cn.weizhis.cms.service.system.SysRoleMenuService;
import cn.weizhis.cms.service.system.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:04
 * @Description:
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 角色列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:role:list")
    public InvokeResult list(@RequestParam Map<String, Object> params){
        InvokeResult result = new InvokeResult();
        //如果不是超级管理员，则只查询自己创建的角色列表
        if(getUserId() != Constant.SUPER_ADMIN){
            params.put("createUserId", getUserId());
        }

        //查询列表数据
        Query query = new Query(params);
        List<SysRoleEntity> list = sysRoleService.queryList(query);
        int total = sysRoleService.queryTotal(query);

        Page pageUtil = new Page(list, total, query.getLimit(), query.getPage());
        result.setData(pageUtil);
        return result;
    }

    /**
     * 角色列表
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:role:select")
    public InvokeResult select(@RequestParam("orgId") Long orgId){
        InvokeResult result = new InvokeResult();
        List<SysRoleEntity> list = null;

        if (orgId != null) {
            Map<String, Object> map = new HashMap<>();

            //如果不是超级管理员，则只查询自己所拥有的角色列表
            if(getUserId() != Constant.SUPER_ADMIN){
                map.put("createUserId", getUserId());
            }
            map.put("orgId", orgId);
            list = sysRoleService.queryList(map);
        }
        result.setData(list);
        return result;
    }

    /**
     * 角色信息
     */
    @RequestMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    public InvokeResult info(@PathVariable("roleId") Long roleId){
        InvokeResult result = new InvokeResult();
        SysRoleEntity role = sysRoleService.queryObject(roleId);

        //查询角色对应的菜单
        List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);

        result.setData(role);
        return result;
    }

    /**
     * 保存角色
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:role:save")
    public InvokeResult save(@RequestBody SysRoleEntity role){
//        ValidatorUtils.validateEntity(role);
        InvokeResult result = new InvokeResult();
        role.setCreateUserId(getUserId());
        sysRoleService.save(role);

        return result.sucess();
    }

    /**
     * 修改角色
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:role:update")
    public InvokeResult update(@RequestBody SysRoleEntity role){
//        ValidatorUtils.validateEntity(role);
        InvokeResult result = new InvokeResult();
        role.setCreateUserId(getUserId());
        sysRoleService.update(role);

        return result.sucess();
    }

    /**
     * 删除角色
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public InvokeResult delete(@RequestBody Long[] roleIds){
        InvokeResult result = new InvokeResult();
        sysRoleService.deleteBatch(roleIds);

        return result.sucess();
    }

}
