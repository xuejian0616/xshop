package com.xshop.project.cms.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_goods_param")
@Data
public class GoodsSpec {
  private static final long serialVersionUID = 369285298572941L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  @ApiModelProperty(value = "商品ID")
  @Column(name = "goods_id")
  private Integer goodsId;
  @ApiModelProperty(value = "商品类目ID")
  @Column(name = "goods_category_id")
  private Integer goodsCategoryId;
  @ApiModelProperty(value = "款号")
  @Column(name = "style_num")
  private String styleNum;
  @ApiModelProperty(value = "规格")
  @Column(name = "norm")
  private String norm;
  @ApiModelProperty(value = "尺码")
  @Column(name = "size")
  private String size;
  @ApiModelProperty(value = "数量")
  @Column(name = "num")
  private Integer num;
  @ApiModelProperty(value = "图片")
  @Column(name = "img")
  private String img;
  @Column(name = "create_time")
  private Date createTime;
  @Column(name = "update_time")
  private Date updateTime;
}