package com.xshop.project.cms.service;

import com.xshop.project.cms.model.Goods;
import com.xshop.project.cms.model.VO.GoodsListVO;
import com.xshop.project.cms.model.VO.GoodsSpecListVO;
import com.xshop.project.cms.model.VO.GoodsVO;

import java.util.List;

/**
 * @program: xshop
 * @Date: 2018/7/1 18:47
 * @Author: Mr.Deng
 * @Description:
 */
public interface CmsGoodsService {

    /**
     * 根据条件分页查询商品对象
     *
     * @param goods 商品信息
     * @return 商品信息集合信息
     */
    public List<Goods> selectGoodsList(Goods goods);

    /**
     * 保存商品信息
     * @return 修改的商品数量
     */
    public int saveGoods(GoodsVO goodsVO);
}
