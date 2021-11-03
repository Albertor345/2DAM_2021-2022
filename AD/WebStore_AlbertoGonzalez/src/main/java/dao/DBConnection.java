package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import configuration.Config;
import lombok.extern.log4j.Log4j2;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Singleton
@Log4j2
public class DBConnection {

    @Inject
    public DBConnection(Config configuration) {
        this.configuration = configuration;
        this.hikariDatasource = getHikariDataSource();
    }

    private HikariDataSource hikariDatasource;
    private Config configuration;

    private HikariDataSource getHikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(Config.getProperties().getProperty("urlDB"));
        config.setUsername(Config.getProperties().getProperty("db_user"));
        config.setPassword(Config.getProperties().getProperty("db_password"));
        config.setDriverClassName(Config.getProperties().getProperty("db_driver"));
        config.setMaximumPoolSize(5);

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource datasource = new HikariDataSource(config);

        return datasource;
    }

    public DataSource getDataSource() {
        return hikariDatasource;
    }

    public Connection getConnection() throws Exception {
        Class.forName(configuration.getProperties().getProperty("db_driver"));
        Connection connection;
        connection = hikariDatasource.getConnection();

        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    public void closePool() {
        hikariDatasource.close();
    }


    public void rollbackConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    public void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    public void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
