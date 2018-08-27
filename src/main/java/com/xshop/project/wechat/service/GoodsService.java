package com.xshop.project.wechat.service;

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
public interface GoodsService {

    /**
     * 根据分类id查询商品列表
     * @param categroyId
     * @return 商品列表
     */
    public List<GoodsListVO> getGoodsByCategory(int categroyId);


    /**
     * 根据商品id获取商品详情
     * @param goodsId
     * @return 商品详情
     */
    public GoodsVO getGoodsInfo(int goodsId);

    /**
     * 根据商品id获取商品规格
     * @param goodsId
     * @return 商品规格
     */
    public GoodsSpecListVO getGoodsSpec(int goodsId);

    /**
     * 获取销量前十的商品列表
     * @return 商品列表
     */
    public List<GoodsListVO> getSalesGoods();


    /**
     * 获取最新上架的10个商品列表
     * @return 商品列表
     */
    public List<GoodsListVO> getNewGoods();
}
