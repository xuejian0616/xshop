package com.xshop;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages={"com.xshop.project.cms.mapper","com.xshop.project.*.*.mapper"})
public class XshopCmsApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(com.xshop.XshopCmsApplication.class, args);
		System.out.println("  XShop 启动成功    ");
	}
}
