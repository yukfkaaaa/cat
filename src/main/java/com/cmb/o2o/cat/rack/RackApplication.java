package com.cmb.o2o.cat.rack;

import com.cmb.o2o.cat.rack.mvc.RequestInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@MapperScan(basePackages = {"com.cmb.o2o.cat.rack.dao"})
@EnableTransactionManagement
public class RackApplication  extends WebMvcConfigurationSupport {

	public static void main(String[] args) {
		SpringApplication.run(RackApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		//registry.addInterceptor此方法添加拦截器
		registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**");
	}

}
