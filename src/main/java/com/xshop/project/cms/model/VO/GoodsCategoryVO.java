package com.xshop.project.cms.model.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

@Data
public class GoodsCategoryVO {
  @ApiModelProperty(value = "类目ID")
  private Integer id;
  @ApiModelProperty(value = "父类目ID=0时，代表的是一级的类目")
  private Integer parentId;
  @ApiModelProperty(value = "类目编号")
  private String code;
  @ApiModelProperty(value = "类目名称")
  private String name;
  @ApiModelProperty(value = "类目图片")
  private String image;

  //子类型列表
  @Transient
  private List<GoodsCategoryVO> subGoodsCategoryList;
}