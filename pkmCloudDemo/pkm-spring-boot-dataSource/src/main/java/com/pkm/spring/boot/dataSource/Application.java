package com.pkm.spring.boot.dataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pkm.spring.boot.dataSource.common.ds.DynamicDataSourceRegister;


/**
 * springboot启动器
 * @author CZH
 */
@Controller
// 扫描mapper
@MapperScan(basePackages = "com.pkm.*.mapper")
// 启动注册动态数据源been
@Import({ DynamicDataSourceRegister.class })
// springboot启动
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@RequestMapping("/")
	String home() {
		return "redirect:queryUserDefPage";
	}
}
