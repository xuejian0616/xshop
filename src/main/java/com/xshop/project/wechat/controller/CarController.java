package com.xshop.project.wechat.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/car")
@Api(value = "CarController", description = "购物车操作")
public class CarController {
    private Logger logger = LoggerFactory.getLogger(getClass());

}