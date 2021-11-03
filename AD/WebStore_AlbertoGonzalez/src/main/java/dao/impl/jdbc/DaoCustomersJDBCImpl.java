package dao.impl.jdbc;

import dao.DAOCustomers;
import dao.DBConnection;
import lombok.extern.log4j.Log4j2;
import model.Customer;
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
public class DaoCustomersJDBCImpl implements DAOCustomers {
    private final DBConnection dbConnection;

    @Inject
    public DaoCustomersJDBCImpl(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public Customer get(Customer customer) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.SELECT_PURCHASE_QUERY)) {
            preparedStatement.setInt(1, customer.getIdCustomer());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Customer.builder()
                        .idCustomer(resultSet.getInt("id_customer"))
                        .name(resultSet.getString("name"))
                        .phone(resultSet.getString("phone"))
                        .address(resultSet.getString("address"))
                        .build();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.SELECT_ALL_CUSTOMERS_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customers.add(Customer.builder()
                        .idCustomer(resultSet.getInt("id_customer"))
                        .name(resultSet.getString("name"))
                        .phone(resultSet.getString("phone"))
                        .address(resultSet.getString("address"))
                        .build());
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return customers;
    }

    @Override
    public boolean add(Customer customer) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.INSERT_CUSTOMER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (preparedStatement.executeUpdate() > 0) {
                customer.setIdCustomer(resultSet.getInt("id_customer"));
            }
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean update(Customer customer) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.UPDATE_CUSTOMER_QUERY)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPhone());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setInt(4, customer.getIdCustomer());
            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
        return false;
    }

    @Override
    public boolean delete(Customer customer) {
        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constantes.DELETE_CUSTOMER_QUERY)) {
            preparedStatement.setInt(1, customer.getIdCustomer());
            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return false;
        }
        return false;
    }
}
