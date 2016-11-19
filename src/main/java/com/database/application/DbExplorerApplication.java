package com.database.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan({"com.database"})
@PropertySource("classpath:/application.properties")
public class DbExplorerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbExplorerApplication.class, args);
	}

}
