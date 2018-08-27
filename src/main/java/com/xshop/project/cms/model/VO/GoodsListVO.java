package com.xshop.project.cms.model.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GoodsListVO {

  @ApiModelProperty(value = "商品id")
  private Integer id;

  @ApiModelProperty(value = "商品名称")
  private String name;

  @ApiModelProperty(value = "商品标题")
  private String title;

  @ApiModelProperty(value = "商品原价，单位为：分")
  private Integer oldPrice;

  @ApiModelProperty(value = "商品现价，单位为：分")
  private Integer price;

  @ApiModelProperty(value = "商品折扣：0.75")
  private float discount;

  @ApiModelProperty(value = "单位")
  private String unit;

  @ApiModelProperty(value = "销量")
  private Integer salesNum;

  @ApiModelProperty(value = "库存数量")
  private Integer stockNum;

  @ApiModelProperty(value = "小图")
  private String smallPic;

}