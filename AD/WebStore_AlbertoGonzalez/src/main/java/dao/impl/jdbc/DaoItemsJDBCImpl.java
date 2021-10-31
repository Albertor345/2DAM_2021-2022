package dao.impl.jdbc;

import dao.DAOItems;
import dao.DBConnection;
import lombok.extern.log4j.Log4j2;
import model.Item;
import producers.annotations.JDBC;
import utils.Constantes;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Log4j2
@JDBC
public class DaoItemsJDBCImpl implements DAOItems {

    private DBConnection dbConnection;

    @Inject
    public DaoItemsJDBCImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void update(Item item) {

    }

    @Override
    public boolean add(Item item) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.INSERT_ITEM_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setString(3, item.getCompany());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                item.setId(resultSet.getInt("id_item"));
            }
            connection.commit();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public boolean delete(Item item) {
        return false;
    }

    @Override
    public Item get(Item item) {
        return null;
    }

    @Override
    public List<Item> getAll() {
        return null;
    }
}
