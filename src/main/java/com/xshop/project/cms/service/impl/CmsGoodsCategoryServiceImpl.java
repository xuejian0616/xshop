package com.xshop.project.cms.service.impl;

import com.xshop.common.constant.UserConstants;
import com.xshop.project.cms.mapper.GoodsCategoryMapper;
import com.xshop.project.cms.model.GoodsCategory;
import com.xshop.project.cms.model.VO.GoodsCategoryVO;
import com.xshop.project.cms.service.CmsGoodsCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: xshop
 *
 * @Date：Created on 2018/7/1 18:30.
 */
@Service
public class CmsGoodsCategoryServiceImpl implements CmsGoodsCategoryService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;


    /**
     * 通过分类Id查询商品分类
     * @return  GoodsCategoryVO
     */
    @Override
    public GoodsCategoryVO getCategoryById(int categroyId){
        if(categroyId == 0){
            GoodsCategoryVO goodsCategoryVo = new GoodsCategoryVO();
            goodsCategoryVo.setName("");

            return goodsCategoryVo;
        }
        GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(categroyId);

        GoodsCategoryVO goodsCategoryVo = new GoodsCategoryVO();
        goodsCategoryVo.setName( goodsCategory.getName() );

        return goodsCategoryVo;
    }


    /**
     * 查询部门管理树
     *
     * @return 所有部门信息
     */
    @Override
    public List<Map<String, Object>> getCategoryTree()
    {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        GoodsCategory goodsCategory = new GoodsCategory();
        goodsCategory.setStatus(1);
        List<GoodsCategory> categoryList = goodsCategoryMapper.selectAll();

        for (GoodsCategory category : categoryList)
        {
            if(Objects.deepEquals(1,category.getStatus())){
                Map<String, Object> categoryMap = new HashMap<String, Object>();
                categoryMap.put("id", category.getId());
                categoryMap.put("pId", category.getParentId());
                categoryMap.put("name", category.getName());
                trees.add(categoryMap);
            }
        }
        return trees;
    }
}