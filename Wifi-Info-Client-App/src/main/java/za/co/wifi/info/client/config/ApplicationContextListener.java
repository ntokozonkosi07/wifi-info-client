package za.co.wifi.info.client.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Component;

@Component
public class ApplicationContextListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.debug("");
        LOGGER.debug("============================================");
        LOGGER.debug("===      Starting Wifi-Info Client       ===");
        LOGGER.debug("Timestamp : {0}", new Date().toString());
        LOGGER.debug("============================================");
        LOGGER.debug("");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.debug("");
        LOGGER.debug("============================================");
        LOGGER.debug("===     Shutting down Wifi-Info Client   ===");
        LOGGER.debug("Timestamp : {0}", new Date().toString());
        LOGGER.debug("============================================");
        LOGGER.debug("");
    }
}
