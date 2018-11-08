package cn.weizhis.cms.entity.system;

import cn.weizhis.cms.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Auther: minliang
 * @Date: 2018/10/8 11:28
 * @Description:
 */
@Data
public class SysRoleEntity extends BaseEntity {
    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建人ID
     */
    private Long createUserId;

    // 视图、逻辑字段
    private List<Long> menuIdList;


}
