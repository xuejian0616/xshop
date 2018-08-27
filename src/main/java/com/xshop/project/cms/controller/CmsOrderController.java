package com.xshop.project.cms.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cms/order")
@Api(value = "OrderController", description = "订单操作")
public class CmsOrderController {
    private Logger logger = LoggerFactory.getLogger(getClass());

}
