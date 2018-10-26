package cn.weizhis.cms.entity.system;

import lombok.Data;

import java.util.List;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:35
 * @Description:
 */
@Data
public class SysMenuEntity {
    /**
     * 菜单ID
     */
    private Long mId;

    /**
     * 父菜单ID，一级菜单为0
     */
    private Long parentId;

    /**
     * 父菜单名称
     */
    private String parentName;

    /**
     * 菜单名称
     */
    private String mName;

    /**
     * 菜单URL
     */
    private String mUrl;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     * 类型     0：目录   1：菜单   2：按钮
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String mIcon;

    /**
     * 排序
     */
    private Integer orderNum;

    //逻辑与视图字段
    private List<SysMenuEntity> list;
    private Boolean open;
}
