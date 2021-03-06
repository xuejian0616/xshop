package com.xshop.project.cms.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: xshop
 *
 * @Date：Created on 2018/9/5 18:30.
 */
@Table(name = "t_shopcar")
@Data
public class Shopcar {
  private static final long serialVersionUID = 369285298572941L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  @ApiModelProperty(value = "用户id")
  @Column(name = "user_id")
  private Integer userId;
  @ApiModelProperty(value = "商品id")
  @Column(name = "goods_id")
  private Integer goodsId;
  @ApiModelProperty(value = "商品名")
  @Column(name = "goods_name")
  private String goodsName;
  @ApiModelProperty(value = "商品图片")
  @Column(name = "img")
  private String img;
  @ApiModelProperty(value = "规则id")
  @Column(name = "spec_id")
  private Integer specId;
  @ApiModelProperty(value = "商品规则")
  @Column(name = "goods_spec")
  private String goodsSpec;
  @ApiModelProperty(value = "数量")
  @Column(name = "num")
  private Integer num;
  @ApiModelProperty(value = "商品原价，单位为：分")
  @Column(name = "old_price")
  private Integer oldPrice;
  @ApiModelProperty(value = "商品现价，单位为：分")
  @Column(name = "price")
  private Integer price;
  @ApiModelProperty(value = "创建时间")
  @Column(name = "create_time")
  private Date createTime;
  @ApiModelProperty(value = "更新时间")
  @Column(name = "update_time")
  private Date updateTime;
}