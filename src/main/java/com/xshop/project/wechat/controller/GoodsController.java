package com.xshop.project.wechat.controller;

import com.xshop.project.cms.model.VO.GoodsListVO;
import com.xshop.project.cms.model.VO.GoodsSpecListVO;
import com.xshop.project.cms.model.VO.GoodsVO;
import com.xshop.project.wechat.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wx/goods")
@Api(value = "GoodsController", description = "商品操作")
public class GoodsController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    GoodsService goodsService;

    @ApiOperation(value = "根据商品分类查询商品列表", notes = "根据商品分类查询商品列表",httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "categroyId", paramType = "query", dataType = "Integer", value = "分类id")})
    @GetMapping(value = "/getGoodsByCategory/{categroyId}")
    public List<GoodsListVO> getGoodsByCategory(@PathVariable String categroyId){

        return goodsService.getGoodsByCategory(Integer.parseInt(categroyId));
    }

    @ApiOperation(value = "根据商品id获取商品详情", notes = "根据商品id获取商品详情",httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "goodsId", paramType = "query", dataType = "Integer", value = "商品id")})
    @GetMapping(value = "/getGoodsInfo")
    public GoodsVO getGoodsInfo(String goodsId){

        return goodsService.getGoodsInfo(Integer.parseInt(goodsId));
    }

    @ApiOperation(value = "根据商品id获取商品规格", notes = "根据商品id获取商品规格",httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "goodsId", paramType = "query", dataType = "Integer", value = "商品id")})
    @GetMapping(value = "/getGoodsSpec/{goodsId}")
    public GoodsSpecListVO getGoodsSpec(@PathVariable String goodsId){

        return goodsService.getGoodsSpec(Integer.parseInt(goodsId));
    }

    @ApiOperation(value = "获取销量前十的商品列表", notes = "获取销量前十的商品列表",httpMethod = "GET")
    @GetMapping(value = "/getSalesGoods")
    public List<GoodsListVO> getSalesGoods(){

        return goodsService.getSalesGoods();
    }

    @ApiOperation(value = "获取最新上架的商品列表", notes = "获取最新上架的商品列表",httpMethod = "GET")
    @GetMapping(value = "/getNewGoods")
    public List<GoodsListVO> getNewGoods(){

        return goodsService.getNewGoods();
    }
}