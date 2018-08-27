package com.xshop.project.cms.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cms/car")
@Api(value = "CarController", description = "购物车操作")
public class CmsCarController {
    private Logger logger = LoggerFactory.getLogger(getClass());

}