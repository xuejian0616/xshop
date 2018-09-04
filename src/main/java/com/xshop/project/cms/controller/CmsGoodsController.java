package com.xshop.project.cms.controller;

import com.xshop.common.exception.base.BaseException;
import com.xshop.framework.aspectj.lang.annotation.Log;
import com.xshop.framework.web.controller.BaseController;
import com.xshop.framework.web.domain.Message;
import com.xshop.framework.web.page.TableDataInfo;
import com.xshop.project.cms.model.Goods;
import com.xshop.project.cms.model.VO.GoodsVO;
import com.xshop.project.cms.service.CmsGoodsService;
import com.xshop.project.system.user.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cms/goods")
@Api(value = "GoodsController", description = "商品操作")
public class CmsGoodsController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private String prefix = "cms/goods";
    @Autowired
    CmsGoodsService cmsGoodsService;



    @RequiresPermissions("cms:goods:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/goods";
    }

    @ApiOperation(value = "查询商品列表", notes = "查询商品列表",httpMethod = "GET")
    @RequiresPermissions("cms:goods:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Goods goods) {
        startPage();
        List<Goods> list = cmsGoodsService.selectGoodsList(goods);
        return getDataTable(list);
    }

    /**
     * 修改商品信息
     */
    @RequiresPermissions("cms:goods:edit")
    @Log(title = "商品管理", action = "商品管理-修改商品")
    @GetMapping("/edit/{goodsId}")
    public String edit(@PathVariable("goodsId") int goodsId, Model model) {
        GoodsVO goods = cmsGoodsService.getGoodsById(goodsId);
        model.addAttribute("goods", goods);
        return prefix + "/edit";
    }
    /**
     * 新增商品
     */
    @RequiresPermissions("cms:goods:add")
    @Log(title = "商品管理", action = "商品管理-新增商品")
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("categorys", "");
        return prefix + "/add";
    }


    /**
     * 保存商品
     */
    @RequiresPermissions("cms:goods:save")
    @Log(title = "商品管理", action = "商品管理-保存商品")
    @PostMapping("/save")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Message save(GoodsVO goodsVO) {
        try {
            cmsGoodsService.saveGoods(goodsVO);
            return Message.success();
        }catch (Exception e){
            throw new BaseException(prefix,e.getMessage());
        }
    }
}