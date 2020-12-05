package com.demo;

import com.demo.equipmentControl.ClientMQTT;
import com.demo.utils.RecordLog;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.demo.project.mapper")
@SpringBootApplication

@EnableConfigurationProperties
@EnableScheduling
    public class Application {
        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }
}
