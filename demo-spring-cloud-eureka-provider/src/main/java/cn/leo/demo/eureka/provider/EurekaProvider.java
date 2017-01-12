package cn.leo.demo.eureka.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaProvider {
	public static void main(String[] args) {
		SpringApplication.run(EurekaProvider.class, args);
	}
}
