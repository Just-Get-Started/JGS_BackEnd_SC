package com.example.teamreviewservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TeamReviewServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamReviewServiceApplication.class, args);
    }

}
