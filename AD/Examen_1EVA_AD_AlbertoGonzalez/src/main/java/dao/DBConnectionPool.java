package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import config.Config;
import lombok.extern.log4j.Log4j2;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j2
public class DBConnectionPool {

    private static HikariDataSource hikariDatasource;

    public DBConnectionPool() {
        if (hikariDatasource == null) {
            this.hikariDatasource = getHikariDataSource();
        }
    }

    private HikariDataSource getHikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(Config.getInstance().getProperty("urlDB"));
        config.setUsername(Config.getInstance().getProperty("db_user"));
        config.setPassword(Config.getInstance().getProperty("db_password"));
        config.setDriverClassName(Config.getInstance().getProperty("db_driver"));
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
        Class.forName(Config.getInstance().getProperty("db_driver"));
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
