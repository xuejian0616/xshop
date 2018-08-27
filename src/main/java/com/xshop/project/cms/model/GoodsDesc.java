package com.xshop.project.cms.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_goods_desc")
@Data
public class GoodsDesc {
  private static final long serialVersionUID = 369285298572941L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "商品ID")
  @Column(name = "goods_id")
  private Integer goodsId;
  @ApiModelProperty(value = "商品详情")
  @Column(name = "goods_desc")
  private String goodsDesc;
  @ApiModelProperty(value = "创建时间")
  @Column(name = "create_time")
  private Date createTime;
  @ApiModelProperty(value = "更新时间")
  @Column(name = "update_time")
  private Date updateTime;
}