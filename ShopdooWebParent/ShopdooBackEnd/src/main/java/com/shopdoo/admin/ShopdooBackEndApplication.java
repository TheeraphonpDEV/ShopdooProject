package com.shopdoo.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.shopdoo.common.entity", "com.shopdoo.admin.user"})
public class ShopdooBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopdooBackEndApplication.class, args);
	}

}
