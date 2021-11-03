/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.yaml.snakeyaml.Yaml;

import javax.inject.Singleton;
import java.util.Map;

@Getter
@Setter
@Log4j2
@Singleton
@PropertySource("propertiesFiles/users.yaml")
@ConfigurationProperties(prefix = "yaml")
public class ConfigYaml {
    private Map<String, String> properties;
 /*   private String user;
    private String pass;
    private String db_user;
    private String db_password;
    private String db_driver;
    private String urlDB;*/

    public ConfigYaml() {
        /*try {

            Yaml yaml = new Yaml();
            Map<String, String> map = yaml.load(yaml.getClass().getResourceAsStream("/propertiesFiles/users.yaml"));
            this.user = map.get("user");
            this.pass = map.get("pass");
            this.db_user = map.get("db_user");
            this.db_password = map.get("db_password");
            this.db_driver = map.get("db_driver");
            this.urlDB = map.get("urlDB");
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }*/
    }

}

