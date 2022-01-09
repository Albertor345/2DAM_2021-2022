/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import javax.inject.Singleton;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Getter
@Setter
@Log4j2
@Singleton
public class Config {
    private static Properties properties;


    public Config() {
        try {
            properties = new Properties();
            properties.loadFromXML(new FileInputStream("propertiesFiles/settings.xml"));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public static Properties getProperties() {
        return properties;
    }

}



