package za.co.wifi.info.client.config;

import java.text.MessageFormat;
import java.text.NumberFormat;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Runtime runtime = Runtime.getRuntime();
        final NumberFormat numberFormat = NumberFormat.getInstance();
        final long maxMemory = runtime.maxMemory();
        final long allocatedMemory = runtime.totalMemory();
        final long freeMemory = runtime.freeMemory();
        final long mb = 1024 * 1024;

        LOGGER.info("============================================");
        LOGGER.info("===       Starting Wifi-Info Client      ===");
        LOGGER.info("============================================");
        LOGGER.info("===              Memory Info             ===");
        LOGGER.info("============================================");
        LOGGER.info(new MessageFormat("Free Memory: {0} MB")
                .format(new Object[]{numberFormat.format(freeMemory / mb)}));
        LOGGER.info(new MessageFormat("Allocated Memory: {0} MB")
                .format(new Object[]{numberFormat.format(allocatedMemory / mb)}));
        LOGGER.info(new MessageFormat("Max Memory: {0} MB")
                .format(new Object[]{numberFormat.format(maxMemory / mb)}));
        LOGGER.info(new MessageFormat("Total free Memory: {0} MB")
                .format(new Object[]{numberFormat.format((freeMemory + (maxMemory - allocatedMemory)) / mb)}));
        LOGGER.info("============================================");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("============================================");
        LOGGER.info("===     Shutting down Wifi-Info Client   ===");
        LOGGER.info("============================================");
    }
}
