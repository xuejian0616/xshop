package com.xshop.project.wechat.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/contentCategory")
@Api(value = "ContentCategoryController", description = "文章分类操作")
public class ContentCategoryController {
    private Logger logger = LoggerFactory.getLogger(getClass());

}