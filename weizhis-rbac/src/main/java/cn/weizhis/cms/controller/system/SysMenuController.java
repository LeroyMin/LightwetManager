package cn.weizhis.cms.controller.system;

import cn.weizhis.cms.common.Constant;
import cn.weizhis.cms.common.InvokeResult;
import cn.weizhis.cms.entity.system.SysMenuEntity;
import cn.weizhis.cms.enums.MenuType;
import cn.weizhis.cms.exception.BizzException;
import cn.weizhis.cms.service.system.SysMenuService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:04
 * @Description:
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 所有菜单列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public List<SysMenuEntity> list(){
        List<SysMenuEntity> menuList = sysMenuService.queryList(new HashMap<String, Object>());

        return menuList;
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @RequestMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public InvokeResult select(){
        InvokeResult result = new InvokeResult();
        //查询列表数据
        List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenuEntity root = new SysMenuEntity();
        root.setId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);
        result.setData(menuList);
        return result;
    }

    /**
     * 角色授权菜单
     */
    @RequestMapping("/perms")
    @RequiresPermissions("sys:menu:perms")
    public InvokeResult perms(){
        InvokeResult result = new InvokeResult();
        //查询列表数据
        List<SysMenuEntity> menuList = null;

        //只有超级管理员，才能查看所有管理员列表
        if(getUserId() == Constant.SUPER_ADMIN){
            menuList = sysMenuService.queryList(new HashMap<String, Object>());
        }else{
            menuList = sysMenuService.queryUserList(getUserId());
        }
        result.setData(menuList);
        return result;
    }

    /**
     * 菜单信息
     */
    @RequestMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public InvokeResult info(@PathVariable("menuId") Long menuId){
        InvokeResult result = new InvokeResult();
        SysMenuEntity menu = sysMenuService.queryObject(menuId);
        result.setData(menu);
        return result;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public InvokeResult save(@RequestBody SysMenuEntity menu){
        InvokeResult result = new InvokeResult();
        //数据校验
        verifyForm(menu);

        sysMenuService.save(menu);

        return result.success();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public InvokeResult update(@RequestBody SysMenuEntity menu){
        InvokeResult result = new InvokeResult();
        //数据校验
        verifyForm(menu);

        sysMenuService.update(menu);

        return result.success();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:menu:delete")
    public InvokeResult delete(long menuId){
        InvokeResult result = new InvokeResult();
        if(menuId <= 30){
            return result.failure("系统菜单，不能删除");
        }

        //判断是否有子菜单或按钮
        List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
        if(menuList.size() > 0){
            return result.failure("请先删除子菜单或按钮");
        }

        sysMenuService.deleteBatch(new Long[]{menuId});

        return result.success();
    }

    /**
     * 用户菜单列表
     */
    @ResponseBody
    @PostMapping(path = "/user")
    public InvokeResult user(){
        InvokeResult result = new InvokeResult();
        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());
        Set<String> permissions =  sysMenuService.getUserPermissions(getUserId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("menuList", menuList);
        jsonObject.put("permissions", permissions);
        result.setData(jsonObject);
        return result;
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenuEntity menu){
        if(StringUtils.isBlank(menu.getName())){
            throw new BizzException("菜单名称不能为空");
        }

        if(menu.getParentId() == null){
            throw new BizzException("上级菜单不能为空");
        }

        //菜单
        if(menu.getType() == MenuType.MENU.getValue()){
            if(StringUtils.isBlank(menu.getUrl())){
                throw new BizzException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = MenuType.CATALOG.getValue();
        if(menu.getParentId() != 0){
            SysMenuEntity parentMenu = sysMenuService.queryObject(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if(menu.getType() == MenuType.CATALOG.getValue() ||
                menu.getType() == MenuType.MENU.getValue()){
            if(parentType != MenuType.CATALOG.getValue()){
                throw new BizzException("上级菜单只能为目录类型");
            }
            return ;
        }

        //按钮
        if(menu.getType() == MenuType.BUTTON.getValue()){
            if(parentType != MenuType.MENU.getValue()){
                throw new BizzException("上级菜单只能为菜单类型");
            }
            return ;
        }
    }
}
