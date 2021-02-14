package com.pj.tbeots;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.pj.tbeots.springboot"})
public class TbeotsWebWarApp {
    public static void main(String[] args) {
        SpringApplication.run(TbeotsWebWarApp.class, args);
    }
}
