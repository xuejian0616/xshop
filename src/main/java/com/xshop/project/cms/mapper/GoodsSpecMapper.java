package com.xshop.project.cms.mapper;

import com.xshop.common.mapper.BaseMapper;
import com.xshop.project.cms.model.GoodsSpec;
import com.xshop.project.cms.model.VO.GoodsSpecVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: xshop
 *
 * @Date：Created on 2018/7/1 18:30.
 */
@Repository
public interface GoodsSpecMapper extends BaseMapper<GoodsSpec> {


    /**
     * 根据商品id查询商品规格列表
     * @param goodsId
     * @return 商品规格列表
     */
    public List<GoodsSpecVO> getGoodsSpec(@Param("goodsId") int goodsId);
}