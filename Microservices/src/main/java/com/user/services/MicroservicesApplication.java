package com.user.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MicroservicesApplication {

	public static void main(String[] args) {
			SpringApplication.run(MicroservicesApplication.class, args);
	
	}
	
	

}
