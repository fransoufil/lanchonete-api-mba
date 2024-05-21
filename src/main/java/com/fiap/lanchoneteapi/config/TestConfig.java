package com.fiap.lanchoneteapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {
//    @Autowired
//    private DBService dbService;
//
//    @Bean
//    public boolean instantiateDatabase() throws ParseException {
//        dbService.instantiateTestDatabase();
//        return true;
//    }
}
