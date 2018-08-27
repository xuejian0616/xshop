package com.xshop.project.cms.model.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Data
public class GoodsSpecVO {
  private Integer id;
  @ApiModelProperty(value = "商品ID")
  private Integer goodsId;
  @ApiModelProperty(value = "商品类目ID")
  private Integer goodsCategoryId;
  @ApiModelProperty(value = "款号")
  private String styleNum;
  @ApiModelProperty(value = "条码")
  private String  barcode;
  @ApiModelProperty(value = "规格")
  private String norm;
  @ApiModelProperty(value = "尺码")
  private String size;
  @ApiModelProperty(value = "数量")
  private Integer num;
  @ApiModelProperty(value = "图片")
  private String img;
}