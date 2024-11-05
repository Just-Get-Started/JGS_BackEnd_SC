package com.example.teammemberservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TeamMemberServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamMemberServiceApplication.class, args);
    }

}
