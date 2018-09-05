package com.xshop.project.cms.service.impl;

import com.xshop.common.utils.CopyUtil;
import com.xshop.project.cms.mapper.GoodsCategoryMapper;
import com.xshop.project.cms.mapper.GoodsDescMapper;
import com.xshop.project.cms.mapper.GoodsMapper;
import com.xshop.project.cms.mapper.GoodsSpecMapper;
import com.xshop.project.cms.model.Goods;
import com.xshop.project.cms.model.GoodsCategory;
import com.xshop.project.cms.model.GoodsDesc;
import com.xshop.project.cms.model.GoodsSpec;
import com.xshop.project.cms.model.VO.GoodsListVO;
import com.xshop.project.cms.model.VO.GoodsSpecListVO;
import com.xshop.project.cms.model.VO.GoodsSpecVO;
import com.xshop.project.cms.model.VO.GoodsVO;
import com.xshop.project.cms.service.CmsGoodsService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CmsGoodsServiceImpl implements CmsGoodsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GoodsSpecMapper goodsSpecMapper;

    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;

    @Autowired
    GoodsDescMapper goodsDescMapper;


    /**
     * 根据条件分页查询商品对象
     *
     * @param goods 商品信息
     * @return 商品信息集合信息
     */
    public List<Goods> selectGoodsList(Goods goods){
        return goodsMapper.selectGoodsList(goods);
    }


    /**
     * 根据条件分页查询商品对象
     *
     * @param goodsId 商品ID
     * @return 商品信息集合信息
     */
    public GoodsVO getGoodsById(int goodsId){
        GoodsVO goodsVO = new GoodsVO();
        //获取商品基本信息
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        if(!Objects.isNull(goods)){
            goodsVO = (GoodsVO)CopyUtil.populate(goods,goodsVO);

            GoodsCategory goodsCategory = new GoodsCategory();
            String thirdCid = goodsVO.getThirdCid();
            String subCid = goodsVO.getSubCid();
            if(!Objects.isNull(thirdCid)){
                goodsCategory = goodsCategoryMapper.selectByPrimaryKey(thirdCid);
            }else if(!Objects.isNull(subCid)){
                goodsCategory = goodsCategoryMapper.selectByPrimaryKey(subCid);
            }else{
                goodsCategory = goodsCategoryMapper.selectByPrimaryKey(goodsVO.getFirstCid());
            }
            goodsVO.setCategoryName(goodsCategory.getName());

            //获取商品详情
            GoodsDesc goodsDesc = new GoodsDesc();
            goodsDesc.setGoodsId(goodsId);
            goodsDesc = goodsDescMapper.selectOne(goodsDesc);
            if(!Objects.isNull(goodsDesc)){
                goodsVO.setGoodsDesc(goodsDesc.getGoodsDesc());
            }else{
                goodsVO.setGoodsDesc("");
            }

            //获取商品规格
            List<GoodsSpecVO> goodsSpecs = goodsSpecMapper.getGoodsSpec(goodsId);
            if(CollectionUtils.isNotEmpty(goodsSpecs)){
                goodsVO.setGoodsSpec(goodsSpecs);
            }else {
                goodsVO.setGoodsSpec(new ArrayList<GoodsSpecVO>());
            }
        }

        return goodsVO;
    }

    /**
     * 保存商品信息
     * @return 修改的商品数量
     */
    public int saveGoods(GoodsVO goodsVO){
        Date date = new Date();
        Integer goodsId = goodsVO.getId();
        List<GoodsSpecVO> goodsSpec = goodsVO.getGoodsSpec();
        int storeNum = goodsSpec.stream().map(x -> x.getNum()).reduce(0,Integer::sum);

        if(Objects.isNull(goodsId)){  //保存商品
            String category = goodsVO.getCategoryId();
            Goods goods = new Goods();
            goods = (Goods)CopyUtil.populate(goodsVO,goods);
            goods.setSalesNum(0);
            goods.setStockNum(storeNum);
            goods.setCreateTime(date);
            goods.setUpdateTime(date);

            //处理商品分类
            GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(category);
            if(goodsCategory.getLevel() == 3 ){
                goods.setThirdCid(goodsCategory.getId()+"");
                goods.setSubCid(goodsCategory.getParentId()+"");
                GoodsCategory goodsCategory1 = goodsCategoryMapper.selectByPrimaryKey(goodsCategory.getParentId());
                goods.setFirstCid(goodsCategory1.getParentId()+"");

            }else if(goodsCategory.getLevel() == 2 ){
                goods.setSubCid(goodsCategory.getId()+"");
                goods.setFirstCid(goodsCategory.getParentId()+"");

            }else{
                goods.setFirstCid(goodsCategory.getId()+"");
            }

            logger.info("insert 之前的goods ：" + goods);
            int id = goodsMapper.insert(goods);
            logger.info("insert 之后的goods ：" + goods);

            //处理商品规格
            List<GoodsSpec> goodsSpecList = new ArrayList<>();
            goodsSpecList = CopyUtil.populateList(goodsSpec,goodsSpecList, GoodsSpec.class);
            for(GoodsSpec goodsSpec1: goodsSpecList){
                goodsSpec1.setGoodsId(goods.getId());
                goodsSpec1.setGoodsCategoryId(Integer.parseInt(category));
            }

            int specNum = goodsSpecMapper.insertList(goodsSpecList);

            //处理商品详情
            GoodsDesc goodsDesc = new GoodsDesc();
            goodsDesc.setGoodsId(id);
            goodsDesc.setCreateTime(date);
            goodsDesc.setUpdateTime(date);
            goodsDesc.setGoodsDesc(goodsVO.getGoodsDesc());


        }else{ //修改商品信息

        }

        return 1;
    }
}