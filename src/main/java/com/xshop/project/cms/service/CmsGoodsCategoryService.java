package com.xshop.project.cms.service;

import com.xshop.project.cms.model.VO.GoodsCategoryVO;

import java.util.List;
import java.util.Map;

/**
 * @program: xshop
 * @Date: 2018/7/1 18:49
 * @Author: Mr.Deng
 * @Description:
 */
public interface CmsGoodsCategoryService {

    /**
     * 通过分类Id查询商品分类
     * @return  GoodsCategoryVO
     */
    public GoodsCategoryVO getCategoryById(int categroyId);

    /**
     * 获取商品分类树
     * @return list
     */
    public List<Map<String, Object>> getCategoryTree();

}
