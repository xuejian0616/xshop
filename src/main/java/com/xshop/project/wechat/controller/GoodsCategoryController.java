package com.xshop.project.wechat.controller;

import com.xshop.project.cms.model.VO.GoodsCategoryVO;
import com.xshop.project.wechat.service.GoodsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wx/goodsCategory")
@Api(value = "GoodsCategoryController", description = "商品分类操作")
public class GoodsCategoryController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @ApiOperation(value = "获取服装的二级分类", notes = "获取服装的二级分类",httpMethod = "get")
    @GetMapping(value = "/getSubCategoryList")
    public List<GoodsCategoryVO> getGoodsSubCategory(){

        return goodsCategoryService.getGoodsSubCategory();
    }
}