package configuration;

import dao.DAOItems;
import dao.DBConnection;
import dao.impl.files.DaoItemsFilesImpl;
import dao.impl.jdbc.DaoItemsJDBCImpl;
import lombok.extern.log4j.Log4j2;

import javax.inject.Singleton;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@Log4j2
@Singleton
public class DAOFactory {
    private Properties daoProps;

    public DAOFactory() {
        setProperties();
    }

    private void setProperties() {
        try {
            daoProps = new Properties();
            daoProps.loadFromXML(Files.newInputStream(Paths.get("propertiesFiles/settings.xml")));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    public DAOItems getDAO() {
        if (daoProps.getProperty("type").equals("JDBC")) {
            return DaoItemsJDBCImpl();
        } else if (daoProps.getProperty("type").equals("Files")) {
            return new DaoItemsFilesImpl();
        } else {
            return null;
        }
    }

}
