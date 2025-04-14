package ru.wildberries.utils.configs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final Logger LOGGER = LogManager.getLogger(AppConfig.class);
    private static final Properties PROPERTIES = new Properties();
    private static final String CONFIG_FILE = "config.properties";

    static {
        loadConfig();
    }

    private static void loadConfig() {
        try (InputStream input = AppConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                LOGGER.error("Unable to find {}", CONFIG_FILE);
                throw new RuntimeException("Configuration file not found: " + CONFIG_FILE);
            }
            PROPERTIES.load(input);
            LOGGER.info("Loaded configuration from {}", CONFIG_FILE);
        } catch (IOException ex) {
            LOGGER.error("Error loading configuration", ex);
            throw new RuntimeException("Failed to load configuration", ex);
        }
    }

    public static String getBaseUrl() {
        return PROPERTIES.getProperty("base.url");
    }

    public static String getProductPath() {
        return PROPERTIES.getProperty("product.path");
    }

    public static String getProductUrl() {
        return getBaseUrl() + getProductPath();
    }

    public static int getTimeout() {
        return Integer.parseInt(PROPERTIES.getProperty("timeout.seconds"));
    }
}
