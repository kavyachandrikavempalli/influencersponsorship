package com.example.influencersponsorship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.data.jdbc.autoconfigure.DataJdbcRepositoriesAutoConfiguration;

@SpringBootApplication(exclude = { DataJdbcRepositoriesAutoConfiguration.class })
public class InfluencersponsorshipApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfluencersponsorshipApplication.class, args);
	}

}
