package com.xshop.project.wechat.vo;

import com.xshop.project.cms.model.Shopcar;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: xshop
 *
 * @Date：Created on 2018/9/5 18:30.
 */
@Data
public class CarListVO {
    @ApiModelProperty("商品数量")
    private int num;
    @ApiModelProperty("价格")
    private double totalPrice;
    @ApiModelProperty("原价")
    private double oldPrice;
    @ApiModelProperty("优惠价格")
    private double savePrice;
    @ApiModelProperty("商品列表")
    private List<Shopcar> carList;
}