package com.xshop.project.wechat.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: xshop
 *
 * @Date：Created on 2018/9/5 18:30.
 */
@Data
public class AddShopCarVO {
    @ApiModelProperty("id")
    private int id;
    @ApiModelProperty("用户id")
    private int userId;
    @ApiModelProperty("商品id")
    private int goodsId;
    @ApiModelProperty("商品名")
    private String goodsName;
    @ApiModelProperty("规格id")
    private int specId;
    @ApiModelProperty("规格")
    private String spec;
    @ApiModelProperty("规格图")
    private String img;
    @ApiModelProperty("数量")
    private int num;
    @ApiModelProperty("价格")
    private double price;
    @ApiModelProperty("原价")
    private double oldPrice;
    @ApiModelProperty("最低价")
    private double lowerPrice;
    @ApiModelProperty("操作类型： 0添加；1减少；2删除；3设置")
    private int type;
}