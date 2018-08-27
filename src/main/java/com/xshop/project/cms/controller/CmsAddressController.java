package com.xshop.project.cms.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cms/address")
@Api(value = "AddressController", description = "收货地址操作")
public class CmsAddressController {
    private Logger logger = LoggerFactory.getLogger(getClass());

}