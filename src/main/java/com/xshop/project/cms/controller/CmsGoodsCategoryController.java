package com.xshop.project.cms.controller;

import com.xshop.project.cms.model.VO.GoodsCategoryVO;
import com.xshop.project.cms.service.CmsGoodsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cms/goodsCategory")
@Api(value = "GoodsCategoryController", description = "商品分类操作")
public class CmsGoodsCategoryController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private String prefix = "cms/category";

    @Autowired
    private CmsGoodsCategoryService goodsCategoryService;


    /**
     * 选择分类树页面
     */
    @GetMapping("/categoryTree/{categoryId}")
    public String categoryTree(@PathVariable("categoryId") int categoryId, Model model)
    {
        model.addAttribute("treeName", goodsCategoryService.getCategoryById(categoryId).getName());
        return prefix + "/tree";
    }

    /**
     * 加载分类列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData()
    {
        List<Map<String, Object>> tree = goodsCategoryService.getCategoryTree();
        return tree;
    }

}