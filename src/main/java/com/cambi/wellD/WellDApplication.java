package com.cambi.wellD;

import com.cambi.wellD.configuration.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(WebConfig.class)
public class WellDApplication {

    public static void main(String[] args) {
        SpringApplication.run(WellDApplication.class, args);
    }
}
