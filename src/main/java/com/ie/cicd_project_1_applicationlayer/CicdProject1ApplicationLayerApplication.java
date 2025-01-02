package com.ie.cicd_project_1_applicationlayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CicdProject1ApplicationLayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CicdProject1ApplicationLayerApplication.class, args);
    }

}
