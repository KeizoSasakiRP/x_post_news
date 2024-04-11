package com.example.news_post;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NewsPostApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsPostApplication.class, args);
	}
	
}
