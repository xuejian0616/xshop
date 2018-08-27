package com.xshop.project.cms.model.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GoodsSpecListVO {
  @ApiModelProperty(value = "规格")
  private List<Map<String,String>> norms;
  @ApiModelProperty(value = "尺码")
  private List<Map<String,String>> sizes;


  @ApiModelProperty(value = "商品规格列表")
  private List<GoodsSpecVO> goodsSpec;
}