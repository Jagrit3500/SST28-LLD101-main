package com.example.metrics;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Loads default metric keys from a properties file.
 *
 * FIXED VERSION:
 * - Uses MetricsRegistry.getInstance()
 * - No direct instantiation
 * - Populates the global singleton instance
 */
public class MetricsLoader {

    public MetricsRegistry loadFromFile(String path) throws IOException {

        Properties props = new Properties();

        // Load properties file
        try (FileInputStream fis = new FileInputStream(path)) {
            props.load(fis);
        }

        // Use Singleton instance
        MetricsRegistry registry = MetricsRegistry.getInstance();

        // Populate metrics into the singleton
        for (String key : props.stringPropertyNames()) {

            String raw = props.getProperty(key, "0").trim();
            long value;

            try {
                value = Long.parseLong(raw);
            } catch (NumberFormatException e) {
                value = 0L;
            }

            registry.setCount(key, value);
        }

        return registry;
    }
}