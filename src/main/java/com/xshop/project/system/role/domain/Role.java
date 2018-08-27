package com.xshop.project.system.role.domain;

import com.xshop.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 角色对象 sys_role
 * 
 * @author xshop
 */
@Data
public class Role extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /** 角色ID */
    private Long roleId;
    /** 角色名 */
    private String roleName;
    /** 角色权限 */
    private String roleKey;
    /** 角色排序 */
    private String roleSort;
    /** 角色状态:0正常,1禁用 */
    private int status;
    /** 用户是否存在此角色标识 默认不存在 */
    private boolean flag = false;
    /** 菜单组 */
    private Long[] menuIds;

}
