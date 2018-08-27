package com.xshop.project.wechat.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/content")
@Api(value = "ContentController", description = "文章操作")
public class ContentController {
    private Logger logger = LoggerFactory.getLogger(getClass());

}