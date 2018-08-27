package com.xshop.project.system.config.domain;

import com.xshop.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 参数配置表 sys_config
 * 
 * @author xshop
 */
@Data
public class Config extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 参数主键 */
    private Integer configId;
    /** 参数名称 */
    private String configName;
    /** 参数键名 */
    private String configKey;
    /** 参数键值 */
    private String configValue;
    /** 系统内置（Y是 N否） */
    private String configType;
}
