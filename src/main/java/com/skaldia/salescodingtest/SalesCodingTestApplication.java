package com.skaldia.salescodingtest;

import com.skaldia.salescodingtest.config.ConfigTaxes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConfigTaxes.class)
public class SalesCodingTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesCodingTestApplication.class, args);
	}

}
