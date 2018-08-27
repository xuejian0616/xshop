package com.xshop.project.system.dict.domain;

import com.xshop.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 字典数据表 sys_dict_data
 * 
 * @author xshop
 */
@Data
public class DictData extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    /** 字典编码 */
    private Long dictCode;
    /** 字典排序 */
    private Long dictSort;
    /** 字典标签 */
    private String dictLabel;
    /** 字典键值 */
    private String dictValue;
    /** 字典类型 */
    private String dictType;
    /** 状态（0正常 1禁用） */
    private int status;

}
