package com.cmb.o2o.cat.rack;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = {"com.cmb.o2o.cat.rack.dao"})
@EnableTransactionManagement
public class RackApplication {

	public static void main(String[] args) {
		SpringApplication.run(RackApplication.class, args);
	}

}
