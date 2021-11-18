package dao.impl.spring;

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
import java.util.ArrayList;
import java.util.List;

@Log4j2
@JDBC
public class DaoItemsSpringImpl implements DAOItems {

    private DBConnection dbConnection;

    @Inject
    public DaoItemsSpringImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public boolean update(Item item) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.UPDATE_ITEM_QUERY)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getCompany());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.setInt(4, item.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return false;
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
                item.setId(resultSet.getInt(1));
            }
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public boolean delete(Item item) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.DELETE_ITEM_QUERY)) {

            preparedStatement.setInt(1, item.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return false;
    }

    @Override
    public boolean deleteAllPurchasesFromAnItem(Item item) {
        Connection connection = null;
        try {
            connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Constantes.DELETE_SALES_FROM_ITEM);
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, item.getId());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(Constantes.DELETE_ITEM_QUERY);
            preparedStatement.setInt(1, item.getId());

            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
            connection.commit();
        } catch (Exception ex) {
            dbConnection.rollbackConnection(connection);
            log.error(ex.getMessage(), ex);
            return false;
        }
        return false;
    }

    @Override
    public Item get(Item item) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.SELECT_ITEM_QUERY)) {
            preparedStatement.setInt(1, item.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                item.setPrice(resultSet.getDouble("price"));
                item.setCompany(resultSet.getString("company"));
                item.setName(resultSet.getString("name"));
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return item;
    }

    @Override
    public List<Item> getAll() {
        List<Item> listItems = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.SELECT_ALL_ITEMS_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listItems.add(Item.builder()
                        .id(resultSet.getInt("id_item"))
                        .name(resultSet.getString("name"))
                        .price(resultSet.getDouble("price"))
                        .company(resultSet.getString("company"))
                        .build());
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return listItems;
    }
}
