package com.xshop.project.wechat.service;

import com.xshop.project.cms.model.VO.GoodsCategoryVO;

import java.util.List;

/**
 * @program: xshop
 * @Date: 2018/7/1 18:49
 * @Author: Mr.Deng
 * @Description:
 */
public interface GoodsCategoryService {

    /**
     * 获取服装的二级分类
     * @return  List<GoodsCategoryVO>
     */
    public List<GoodsCategoryVO> getGoodsSubCategory();
}
