package com.xshop.project.system.dict.domain;

import com.xshop.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 字典类型对象 sys_dict_type
 * 
 * @author xshop
 */
@Data
public class DictType extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /** 字典主键 */
    private Long dictId;
    /** 字典名称 */
    private String dictName;
    /** 字典类型 */
    private String dictType;
    /** 状态（0正常 1禁用） */
    private int status;

}
