/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConfigProperties {
    private static ConfigProperties configurationProperties;
    private Properties properties;

    public String getProperty(String key) {
        return (String) properties.get(key);
    }

    private ConfigProperties() {

    }

}

