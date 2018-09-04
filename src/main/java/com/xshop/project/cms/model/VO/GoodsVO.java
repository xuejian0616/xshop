package com.xshop.project.cms.model.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class GoodsVO {
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
  @ApiModelProperty(value = "商品最低价，单位为：分")
  private Integer lowerPrice;
  @ApiModelProperty(value = "商品折扣：0.75")
  private float discount;     
  @ApiModelProperty(value = "单位")
  private String unit;
  @ApiModelProperty(value = "销量")
  private Integer salesNum;
  @ApiModelProperty(value = "库存数量")
  private Integer stockNum;
  @ApiModelProperty(value = "商品条形码")
  private String barcode;
  @ApiModelProperty(value = "小图")
  private String smallPic;
  @ApiModelProperty(value = "主图一")
  private String picOne;
  @ApiModelProperty(value = "主图二")
  private String picTwo;
  @ApiModelProperty(value = "主图三")
  private String picThree;
  @ApiModelProperty(value = "主图四")
  private String picFour;
  @ApiModelProperty(value = "主图五")
  private String picFive;
  @ApiModelProperty(value = "一级类目")
  private String firstCid;
  @ApiModelProperty(value = "二级类目")
  private String subCid;
  @ApiModelProperty(value = "三级类目")
  private String thirdCid;
  @ApiModelProperty(value = "商品规格列表")
  private List<GoodsSpecVO> goodsSpec;
  @ApiModelProperty(value = "商品图片列表")
  private List<String> images;
  @ApiModelProperty(value = "商品详情")
  private String goodsDesc;
  @ApiModelProperty(value = "商品分类")
  private String categoryId;
  @ApiModelProperty(value = "分类名称")
  private String categoryName;
}