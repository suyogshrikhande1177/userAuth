package com.practice.userAuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.practice.*")
//@PropertySources({
//		@PropertySource(value = "classpath:ResponseHeaders.properties", ignoreResourceNotFound=true)
//})
public class userAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(userAuthApplication.class, args);
	}
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(UserAuthApplication.class);
//	}
}