package com.xshop.project.wechat.controller;

import com.xshop.project.wechat.vo.CreateOrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/order")
@Api(value = "OrderController", description = "订单操作")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public OrderController() {
    }

    @ApiOperation(
            value = "商品详情直接购买",
            notes = "商品详情直接购买",
            httpMethod = "POST"
    )
    @PostMapping({"/directBuy"})
    public int directBuy(@Validated @RequestBody CreateOrderVO createOrderVO) {

        return 1;
    }

    @ApiOperation(
            value = "购物车提交订单",
            notes = "购物车提交订单",
            httpMethod = "POST"
    )
    @PostMapping({"/createOrder"})
    public int createOrder(@Validated @RequestBody CreateOrderVO createOrderVO) {

        return 1;
    }
}
