package za.co.wifi.info.client.config;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "za.co.wifi.info")
public class ApplicationConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class.getName());

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationConfiguration.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return (String[] args) -> {

            LOGGER.debug("");
            LOGGER.debug("=====================================================");
            LOGGER.debug("=== Inspecting the beans provided by Spring Boot  ===");
            LOGGER.debug("=====================================================");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                LOGGER.debug("Name : {0}", beanName);
            }

            LOGGER.debug("");
            LOGGER.debug("=====================================================");
            LOGGER.debug("");
        };
    }
}
