package me.citerana.deadsea.tools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigurationWriter {
    public static void main(String[] args) {

        Properties properties = new Properties();
        OutputStream output = null;

        try {
            output = new FileOutputStream("config.properties");

            properties.setProperty("token", "NDIzNjkyMDQzNTkyNjYzMDQx.DY0MEw.i3n1r2fI5cg7u7aj9e3d0MJP54k");
            // add more properties here

            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
