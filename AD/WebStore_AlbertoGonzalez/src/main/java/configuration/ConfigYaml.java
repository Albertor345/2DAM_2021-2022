/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Laura
 */
@Getter
@Setter
@NoArgsConstructor
@Log4j2
public class ConfigYaml {

    private static ConfigYaml config;
    private String user;
    private String pass;
    private String db_user;
    private String db_password;
    private String db_driver;
    private String urlDB;

    public static ConfigYaml getInstance() {
        if (config == null) {
            try {
                Yaml yaml = new Yaml();
                config = yaml.loadAs(new FileInputStream("propertiesFiles/users.yml"), ConfigYaml.class);
            } catch (FileNotFoundException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
        return config;
    }


}
