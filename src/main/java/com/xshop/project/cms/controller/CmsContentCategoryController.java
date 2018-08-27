package com.xshop.project.cms.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cms/contentCategory")
@Api(value = "ContentCategoryController", description = "文章分类操作")
public class CmsContentCategoryController {
    private Logger logger = LoggerFactory.getLogger(getClass());

}