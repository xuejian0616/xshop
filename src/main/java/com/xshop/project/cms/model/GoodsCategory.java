package com.xshop.project.cms.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "t_goods_category")
@Data
public class GoodsCategory {
  private static final long serialVersionUID = 369285298572941L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "类目ID")
  @Column(name = "id")
  private Integer id;
  @ApiModelProperty(value = "父类目ID=0时，代表的是一级的类目")
  @Column(name = "parent_id")
  private Integer parentId;
  @ApiModelProperty(value = "类目编号")
  @Column(name = "code")
  private String code;
  @ApiModelProperty(value = "类目名称")
  @Column(name = "name")
  private String name;
  @ApiModelProperty(value = "类目图片")
  @Column(name = "image")
  private String image;
  @ApiModelProperty(value = "状态。可选值:1(正常),2(删除)")
  @Column(name = "status")
  private Integer status;        
  @ApiModelProperty(value = "级别")
  @Column(name = "level")
  private Integer level;      
  @ApiModelProperty(value = "排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数")
  @Column(name = "sort_order")
  private Integer sortOrder;
  @ApiModelProperty(value = "该类目是否为父类目，1为true，0为false")
  @Column(name = "is_parent")
  private Integer isParent;
  @ApiModelProperty(value = "创建时间")
  @Column(name = "create_time")
  private Date createTime;
  @ApiModelProperty(value = "创建时间")
  @Column(name = "update_time")
  private Date updateTime;

  //子类型列表
  @Transient
  private List<GoodsCategory> subGoodsCategoryList;
}