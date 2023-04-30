package com.saucedemo.util;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class PropertiesLoader {

    public static Properties loadProperties(String fileName) {
        log.info("Properties are being loaded from file: {}", fileName);
        Properties properties = new Properties();
        try (InputStream input = PropertiesLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    public static Properties loadProperties() {
        return loadProperties("configuration.properties");
    }
}
