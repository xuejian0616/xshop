package com.xshop.project.cms.mapper;

import com.xshop.common.mapper.BaseMapper;
import com.xshop.project.cms.model.GoodsCategory;
import com.xshop.project.cms.model.VO.GoodsCategoryVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: xshop
 *
 * @Date：Created on 2018/7/1 18:30.
 */
@Repository
public interface GoodsCategoryMapper extends BaseMapper<GoodsCategory> {

    /**
     * 根据商品的二级分类获取下级分类
     * @return  List<GoodsCategoryVO>
     */
    public List<GoodsCategoryVO> getGoodsSubCategory(@Param("parentId") int parentId);


}