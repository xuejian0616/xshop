package com.xshop.project.wechat.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/address")
@Api(value = "AddressController", description = "收货地址操作")
public class AddressController {
    private Logger logger = LoggerFactory.getLogger(getClass());

}