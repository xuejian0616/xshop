package com.xshop.project.cms.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_order")
@Data
public class Order {
  private static final long serialVersionUID = 369285298572941L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "订单id")
  @Column(name = "id")
  private String id;
  @ApiModelProperty(value = "用户id")
  @Column(name = "user_id")
  private Integer userId;
  @ApiModelProperty(value = "实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分")
  @Column(name = "payment")
  private String payment;
  @ApiModelProperty(value = "折扣金额。精确到2位小数;单位:元。如:200.07，表示:200元7分")
  @Column(name = "discount")
  private String discount;
  @ApiModelProperty(value = "支付类型，0、现金，1、微信支付，2、支付宝支付")
  @Column(name = "payment_type")
  private Integer paymentType;
  @ApiModelProperty(value = "邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分")
  @Column(name = "post_fee")
  private String postFee;
  @ApiModelProperty(value = "状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭")
  @Column(name = "status")
  private Integer status;      
  @ApiModelProperty(value = "订单创建时间")
  @Column(name = "create_time")
  private Date createTime;
  @ApiModelProperty(value = "订单更新时间")
  @Column(name = "update_time")
  private Date updateTime;
  @ApiModelProperty(value = "付款时间")
  @Column(name = "payment_time")
  private Date paymentTime;
  @ApiModelProperty(value = "发货时间")
  @Column(name = "consign_time")
  private Date consignTime;
  @ApiModelProperty(value = "交易完成时间")
  @Column(name = "end_time")
  private Date endTime;
  @ApiModelProperty(value = "交易关闭时间")
  @Column(name = "close_time")
  private Date closeTime;
  @ApiModelProperty(value = "发货类型")
  @Column(name = "delivery_type")
  private Integer deliveryType;
  @ApiModelProperty(value = "收货地址")
  @Column(name = "address_id")
  private Integer addressId;
  @ApiModelProperty(value = "物流名称")
  @Column(name = "shipping_name")
  private String shippingName;
  @ApiModelProperty(value = "物流单号")
  @Column(name = "shipping_code")
  private String shippingCode;
  @ApiModelProperty(value = "买家留言")
  @Column(name = "buyer_message")
  private String buyerMessage;
  @ApiModelProperty(value = "买家昵称")
  @Column(name = "buyer_nick")
  private String buyerNick;
  @ApiModelProperty(value = "买家是否已经评价")
  @Column(name = "buyer_rate")
  private Integer buyerRate;
}