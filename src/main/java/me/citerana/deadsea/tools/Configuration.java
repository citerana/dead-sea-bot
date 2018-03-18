package me.citerana.deadsea.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private Properties properties;

    public Configuration() {
        InputStream input = null;
        properties = new Properties();

        try {
            input = new FileInputStream("config.properties");
            properties.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getToken() {
        return properties.getProperty("token");
    }
}
