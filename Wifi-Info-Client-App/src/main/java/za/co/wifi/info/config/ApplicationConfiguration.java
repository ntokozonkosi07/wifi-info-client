package za.co.wifi.info.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"za.co.wifi.info.client", "za.co.wifi.info.remote.client"})
public class ApplicationConfiguration {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationConfiguration.class, args);
    }
}
