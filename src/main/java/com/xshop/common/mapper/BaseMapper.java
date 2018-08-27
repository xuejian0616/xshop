package com.xshop.common.mapper;

import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用mapper接口
 * @param <T> 映射类型
 */
@Component
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
