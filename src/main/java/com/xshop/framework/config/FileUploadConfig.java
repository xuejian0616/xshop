package com.xshop.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

@Configuration
public class FileUploadConfig {
	/**
	 * 七牛文件上传配置
	 * @return MultipartConfigElement
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement(
			@Value("${qiniu.maxFileSize}") String maxFileSize,
			@Value("${qiniu.maxRequestSize}") String maxRequestSize) {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 单个文件最大
		factory.setMaxFileSize(maxFileSize);
		// 设置总上传数据总大小
		factory.setMaxRequestSize(maxRequestSize);
		return factory.createMultipartConfig();
	}
}
 
