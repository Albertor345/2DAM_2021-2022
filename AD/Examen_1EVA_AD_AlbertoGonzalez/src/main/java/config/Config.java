package config;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class Config {
    private static Properties properties;

    public Config() {
        if (properties == null) {
            try {
                properties = new Properties();
                properties.loadFromXML(new FileInputStream("propertiesFiles/settings.xml"));
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public static Properties getInstance() {
        if (properties == null) {
           Config config = new Config();
        }
        return properties;
    }


}
