package cn.weizhis.cms.entity.system;

import lombok.Data;

import java.util.List;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:37
 * @Description:
 */
@Data
public class SysDeptEntity {
    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 上级部门ID，一级部门为0
     */
    private Long parentId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 上级部门名称
     */
    private String parentName;

    /**
     * 排序
     */
    private Integer orderNum;
    private Integer delFlag;

    /**
     * ztree属性
     */
    private Boolean open;
    private List<?> list;
}
