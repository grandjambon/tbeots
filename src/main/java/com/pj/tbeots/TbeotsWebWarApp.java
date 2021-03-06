package com.pj.tbeots;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages={"com.pj.tbeots.springboot"})
public class TbeotsWebWarApp extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TbeotsWebWarApp.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(TbeotsWebWarApp.class, args);
    }
}
