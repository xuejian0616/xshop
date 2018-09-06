package com.xshop.project.cms.mapper;

import com.xshop.common.mapper.BaseMapper;
import com.xshop.project.cms.model.Shopcar;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author: xshop
 *
 * @Date：Created on 2018/9/5 18:30.
 */
@Repository
public interface CarMapper extends BaseMapper<Shopcar> {

    List<Shopcar> getOrderShopcarList(@Param("userId") int userId, @Param("carIds") List<Integer> carIds);
}