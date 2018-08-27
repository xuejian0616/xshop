package com.xshop.project.cms.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_address")
@Data
public class Address {
  private static final long serialVersionUID = 369285298572941L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  @ApiModelProperty(value = "用户id")
  @Column(name = "user_id")
  private Integer userId;
  @ApiModelProperty(value = "收货人")
  @Column(name = "name")
  private String name;
  @ApiModelProperty(value = "电话")
  @Column(name = "mobile")
  private String mobile;
  @ApiModelProperty(value = "省份")
  @Column(name = "province")
  private String province;
  @ApiModelProperty(value = "城市")
  @Column(name = "city")
  private String city;
  @ApiModelProperty(value = "区/县")
  @Column(name = "district")
  private String district;
  @ApiModelProperty(value = "收货地址，如：xx路xx号")
  @Column(name = "address")
  private String address;
  @ApiModelProperty(value = "邮政编码,如：310001")
  @Column(name = "zip")
  private String zip;
  @ApiModelProperty(value = "创建时间")
  @Column(name = "create_time")
  private Date createTime;
  @ApiModelProperty(value = "更新时间")
  @Column(name = "update_time")
  private Date updateTime;
}