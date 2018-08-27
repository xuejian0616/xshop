package com.xshop.project.cms.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cms/content")
@Api(value = "ContentController", description = "文章操作")
public class CmsContentController {
    private Logger logger = LoggerFactory.getLogger(getClass());

}