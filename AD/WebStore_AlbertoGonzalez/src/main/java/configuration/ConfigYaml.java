/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import lombok.*;
import org.yaml.snakeyaml.Yaml;

import javax.inject.Singleton;

/**
 *
 * @author Laura
 */
@Getter
@Setter
@NoArgsConstructor
public class ConfigYaml {

    private String user;
    private String pass;

    private static ConfigYaml config;


    public static ConfigYaml getInstance() {
        if (config == null) {
            try {
                Yaml yaml = new Yaml();
                config = yaml.loadAs(new FileInputStream("propertiesFiles/users.yml"), ConfigYaml.class);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ConfigYaml.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ConfigYaml.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return config;
    }



}
